package nl.blokjeom.blokjeomapi.products.boardgames.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import nl.blokjeom.blokjeomapi.products.domain.Product
import java.time.Instant

@Entity
data class BoardGame(
    @Id override val id: String,
    override val name: String,
    override val year: Int,
    override val imageUrl: String,
    override val creationTime: Instant,
    override val modificationTime: Instant,
    override val rentalPricePerWeek: Int,
    override val available: Boolean,
    override val description: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val playingTime: Int,
    val minPlayTime: Int,
    val maxPlayTime: Int,
    val age: Int,
): Product()