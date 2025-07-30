package nl.blokjeom.blokjeomapi.orders.services

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.transaction.Transactional
import nl.blokjeom.blokjeomapi.mail.config.MailConfigurationProperties
import nl.blokjeom.blokjeomapi.orders.domain.entities.ProductOrder
import nl.blokjeom.blokjeomapi.orders.repositories.OrderRepository
import nl.blokjeom.blokjeomapi.products.boardgames.services.BoardGameService
import nl.blokjeom.blokjeomapi.products.domain.Product
import nl.blokjeom.blokjeomapi.products.domain.ProductType
import nl.blokjeom.blokjeomapi.products.lego.services.LegoService
import org.springframework.stereotype.Service
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

    @Transactional
    fun order(productOrder: ProductOrder): ProductOrder {
        logger.debug { "Sending order confirmations for product: ${productOrder.id}" }
        sendEmailToClient(productOrder)
        sendEmailToBlokjeOm(productOrder)

        setProductAsUnavailable(productOrder)

        return orderRepository.save(productOrder)
    }

    private fun sendEmailToClient(productOrder: ProductOrder) {
        mailService.sendSimpleMessage(
            productOrder.client.emailAddress,
            "Bedankt voor je bestelling bij Blokje Om",
            "Zorg je dat je nog even de betaling afrondt? Pas dan is je bestelling definitief."
        )
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
        val product: Product = when(productOrder.productType) {
            ProductType.LEGO_SET -> legoService.getOneSet(productOrder.productId).getOrNull() as Product
            ProductType.BOARD_GAME -> boardGameService.getOneGame(productOrder.productId).getOrNull() as Product
        }
        product.available = false
    }
}
