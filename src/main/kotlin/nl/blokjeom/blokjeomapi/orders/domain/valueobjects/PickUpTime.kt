package nl.blokjeom.blokjeomapi.orders.domain.valueobjects

import jakarta.persistence.Embeddable
import java.time.Instant

@Embeddable
data class PickUpTime (
    val startTime: Instant,
    val endTime: Instant,
)
