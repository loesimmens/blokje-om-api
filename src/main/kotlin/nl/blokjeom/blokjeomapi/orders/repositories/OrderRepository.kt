package nl.blokjeom.blokjeomapi.orders.repositories

import nl.blokjeom.blokjeomapi.orders.domain.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository: JpaRepository<Order, UUID>
