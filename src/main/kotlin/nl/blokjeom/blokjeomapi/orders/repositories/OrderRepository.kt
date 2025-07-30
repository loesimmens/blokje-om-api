package nl.blokjeom.blokjeomapi.orders.repositories

import nl.blokjeom.blokjeomapi.orders.domain.entities.ProductOrder
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository: JpaRepository<ProductOrder, UUID>
