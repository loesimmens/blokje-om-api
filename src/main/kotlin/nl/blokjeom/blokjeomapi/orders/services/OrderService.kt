package nl.blokjeom.blokjeomapi.orders.services

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.transaction.Transactional
import nl.blokjeom.blokjeomapi.mail.config.MailConfigurationProperties
import nl.blokjeom.blokjeomapi.mail.service.MailService
import nl.blokjeom.blokjeomapi.orders.domain.entities.ProductOrder
import nl.blokjeom.blokjeomapi.orders.repositories.OrderRepository
import nl.blokjeom.blokjeomapi.products.boardgames.services.BoardGameService
import nl.blokjeom.blokjeomapi.products.domain.Product
import nl.blokjeom.blokjeomapi.products.domain.ProductType
import nl.blokjeom.blokjeomapi.products.lego.services.LegoService
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.jvm.optionals.getOrNull


@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val mailService: MailService,
    private val mailConfigurationProperties: MailConfigurationProperties,
    private val legoService: LegoService,
    private val boardGameService: BoardGameService,
) {
    val logger = KotlinLogging.logger {}
    companion object {
        const val PATTERN_FORMAT: String = "EEEE d MMMM yyyy H:mm"
    }

    @Transactional
    fun order(productOrder: ProductOrder): ProductOrder {
        logger.debug { "Sending order confirmations for product: ${productOrder.id}" }
        sendEmailToClient(productOrder)
        sendEmailToBlokjeOm(productOrder)

//        setProductAsUnavailable(productOrder) todo put back

        return orderRepository.save(productOrder)
    }

    private fun sendEmailToClient(productOrder: ProductOrder) =
        mailService.sendMessageUsingThymeleafTemplate(
            productOrder.client.emailAddress,
            "Bedankt voor je bestelling bij Blokje Om",
            mailConfigurationProperties.clientMailTemplateFileName,
            clientMailTemplateModel(productOrder),
            mailConfigurationProperties.logoPath
        )

    private fun clientMailTemplateModel(productOrder: ProductOrder): MutableMap<String, Any> {
        val product = getProductFromOrder(productOrder)
        val mailTemplateModel = mutableMapOf<String, Any>()
        mailTemplateModel.put("firstName", productOrder.client.firstName)
        mailTemplateModel.put("productName", product.name)
        mailTemplateModel.put("startDate", formatInstant(productOrder.pickUpTime.startTime))
        mailTemplateModel.put("endDate", formatInstant(productOrder.pickUpTime.endTime))
        mailTemplateModel.put("price", product.rentalPricePerWeek / 100)
        mailTemplateModel.put("productImage", product.imageUrl)
        return mailTemplateModel
    }

    private fun sendEmailToBlokjeOm(productOrder: ProductOrder) {
        mailService.sendSimpleMessage(
            mailConfigurationProperties.blokjeOmEmail,
            "Besteld: product ${productOrder.productId}, door ${productOrder.client.firstName} ${productOrder.client.lastName}",
            "Check of de betaling binnen is."
        )
    }

    fun setProductAsUnavailable(productOrder: ProductOrder) {
        logger.debug { "Set product ${productOrder.productId} as unavailable" }
        val product: Product = getProductFromOrder(productOrder)
        product.available = false
    }

    private fun getProductFromOrder(productOrder: ProductOrder): Product = when (productOrder.productType) {
        ProductType.LEGO_SET -> legoService.getOneSet(productOrder.productId).getOrNull() as Product
        ProductType.BOARD_GAME -> boardGameService.getOneGame(productOrder.productId)
            .getOrNull() as Product
    }

    private fun formatInstant(instant: Instant): String{
        val locale = Locale.Builder().setLanguageTag("nl").build()
        val formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withLocale(locale)
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }
}
