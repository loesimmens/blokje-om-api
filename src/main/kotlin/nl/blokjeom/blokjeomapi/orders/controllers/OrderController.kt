package nl.blokjeom.blokjeomapi.orders.controllers

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.orders.domain.entities.Order
import nl.blokjeom.blokjeomapi.orders.services.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClientException

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = ["http://localhost:4200"]) // todo remove
class OrderController(private val orderService: OrderService) {
    private val logger = KotlinLogging.logger { }

    @PostMapping
    fun order(@RequestBody order: Order): ResponseEntity<Order> {
        try {
            val savedOrder = orderService.order(order)
            return ResponseEntity.ok(savedOrder)
        } catch (e: RestClientException) {
            logger.error(e) { "Error posting order: ${e.message}" }
            return ResponseEntity.internalServerError().build()
        }
    }
}
