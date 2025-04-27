package nl.blokjeom.blokjeomapi.orders.services

import nl.blokjeom.blokjeomapi.orders.domain.entities.Order
import nl.blokjeom.blokjeomapi.orders.repositories.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    fun order(order: Order): Order {
        return order // todo
    }
}
