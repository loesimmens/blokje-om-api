package nl.blokjeom.blokjeomapi.lego.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import nl.blokjeom.blokjeomapi.lego.domain.LegoTheme
import java.time.Instant

@Entity
data class LegoSet(
    @Id val setId: String,
    val name: String,
    val year: Int,
    val theme: LegoTheme,
    val numberOfParts: Int,
    val imageUrl: String,
    val creationTime: Instant,
    val modificationTime: Instant,
    val rentalPricePerWeek: Double,
    val buildTogether: Boolean,
    val available: Boolean,
)
