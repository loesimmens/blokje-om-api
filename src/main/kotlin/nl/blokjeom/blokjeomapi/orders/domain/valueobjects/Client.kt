package nl.blokjeom.blokjeomapi.orders.domain.valueobjects

import jakarta.persistence.Embeddable

@Embeddable
data class Client(
    val firstName: String,
    val middleName: String? = null,
    val lastName: String,
    val street: String,
    val number: String,
    val numberAddition: String? = null,
    val postalCode: String,
    val town: String,
    val phoneNumber: String,
    val emailAddress: String
)
