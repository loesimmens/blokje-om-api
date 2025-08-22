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
        const val BEDANKT = "Bedankt voor je bestelling bij Blokje Om"
    }

    @Transactional
    fun order(productOrder: ProductOrder): ProductOrder {
        logger.debug { "Sending order confirmations for product: ${productOrder.id}" }

        sendEmailToClient(productOrder)
        sendEmailToBlokjeOm(productOrder)

        setProductAsUnavailable(productOrder)

        return orderRepository.save(productOrder)
    }

    private fun sendEmailToClient(productOrder: ProductOrder) =
        mailService.sendMessageUsingThymeleafTemplate(
            productOrder.client.emailAddress,
            BEDANKT,
            mailConfigurationProperties.clientMailTemplateFileName,
            mailTemplateModel(productOrder, getClientNameFromOrder(productOrder)),
            mailConfigurationProperties.logoPath
        )

    private fun sendEmailToBlokjeOm(productOrder: ProductOrder) {
        val clientName = getClientNameFromOrder(productOrder)
        mailService.sendMessageUsingThymeleafTemplate(
            mailConfigurationProperties.blokjeOmEmail,
            "Besteld: product ${productOrder.productId}, door $clientName",
            mailConfigurationProperties.blokjeOmMailTemplateFileName,
            mailTemplateModel(productOrder, clientName),
            mailConfigurationProperties.logoPath
        )
    }

    private fun mailTemplateModel(productOrder: ProductOrder, clientName: String): MutableMap<String, Any> {
        val product = getProductFromOrder(productOrder)
        val clientStreetLine = getStreetLine(productOrder.client.street, productOrder.client.number, productOrder.client.numberAddition)

        return mutableMapOf<String, Any>().apply {
            this["clientFirstName"] = productOrder.client.firstName
            this["clientName"] = clientName
            this["clientStreetLine"] = clientStreetLine
            this["clientPostalCode"] = productOrder.client.postalCode
            this["clientTown"] = productOrder.client.town
            this["clientEmailAddress"] = productOrder.client.emailAddress
            this["clientPhoneNumber"] = productOrder.client.phoneNumber
            this["productName"] = product.name
            this["productId"] = product.id
            this["startDate"] = formatInstant(productOrder.pickUpTime.startTime)
            this["endDate"] = formatInstant(productOrder.pickUpTime.endTime)
            this["price"] = product.rentalPricePerWeek / 100
            this["productImage"] = product.imageUrl
        }
    }

    private fun getClientNameFromOrder(productOrder: ProductOrder): String {
        val middleName = if (productOrder.client.middleName != null) {
            productOrder.client.middleName + " "
        } else {
            ""
        }
        return "${productOrder.client.firstName} $middleName${productOrder.client.lastName}"
    }

    private fun getStreetLine(street: String, number: String, numberAddition: String?): String {
        val numberAddition = if (numberAddition != null) {
            "$numberAddition"
        } else {
            ""
        }
        return "$street $number$numberAddition"
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
