package nl.blokjeom.blokjeomapi.orders.services

import nl.blokjeom.blokjeomapi.orders.domain.entities.ProductOrder
import nl.blokjeom.blokjeomapi.orders.repositories.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    fun order(productOrder: ProductOrder): ProductOrder {
        return orderRepository.save(productOrder)
    }
}
