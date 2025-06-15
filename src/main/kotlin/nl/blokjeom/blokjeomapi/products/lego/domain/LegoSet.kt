package nl.blokjeom.blokjeomapi.products.lego.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import nl.blokjeom.blokjeomapi.products.domain.Product
import java.time.Instant

@Entity
class LegoSet(
    @Id override val id: String,
    override val name: String,
    override val year: Int,
    val theme: LegoTheme,
    val numberOfParts: Int,
    override val imageUrl: String,
    override val creationTime: Instant,
    override val modificationTime: Instant,
    override val rentalPricePerWeek: Int,
    val buildTogether: Boolean,
    override val available: Boolean,
    override val description: String,
): Product()