package nl.blokjeom.blokjeomapi.products.domain

import java.time.Instant

abstract class Product {
    abstract val id: String
    abstract val name: String
    abstract val year: Int
    abstract val imageUrl: String
    abstract val creationTime: Instant
    abstract val modificationTime: Instant
    abstract val rentalPricePerWeek: Int
    abstract val available: Boolean
    abstract val description: String
}