package nl.blokjeom.blokjeomapi.orders.domain.valueobjects

import java.time.Instant

data class PickUpTime (
    val startTime: Instant,
    val endTime: Instant,
)
