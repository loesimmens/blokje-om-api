package nl.blokjeom.blokjeomapi.orders.services

import jakarta.transaction.Transactional
import nl.blokjeom.blokjeomapi.orders.domain.entities.ProductOrder
import nl.blokjeom.blokjeomapi.orders.repositories.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val mailService: MailService
) {
    @Transactional
    fun order(productOrder: ProductOrder): ProductOrder {
        sendEmailToClient(productOrder)
        sendEmailToBlokjeOm(productOrder)

        setProductAsUnavailable(productOrder)

        return orderRepository.save(productOrder)
    }

    private fun sendEmailToClient(productOrder: ProductOrder) {
        mailService.sendSimpleMessage(
            productOrder.client.emailAddress,
            "Je bestelling is doorgestuurd",
            "Zorg je dat je nog even de betaling afrond? Pas dan is je bestelling compleet."
        )
    }

    private fun sendEmailToBlokjeOm(productOrder: ProductOrder) {
        // todo
    }

    fun setProductAsUnavailable(productOrder: ProductOrder) {
        // todo
    }
}
