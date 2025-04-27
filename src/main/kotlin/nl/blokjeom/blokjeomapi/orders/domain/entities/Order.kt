package nl.blokjeom.blokjeomapi.orders.domain.entities

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import nl.blokjeom.blokjeomapi.orders.domain.valueobjects.Client
import nl.blokjeom.blokjeomapi.orders.domain.valueobjects.PickUpTime
import java.util.UUID
import javax.annotation.processing.Generated

@Entity
data class Order(
    @Id @Generated val id: UUID = UUID.randomUUID(),
    val productId: String,
    @Embedded
    @AttributeOverrides(
        value =
            [
                AttributeOverride(
                    name = "firstName",
                    column = Column(name = "client_first_name")
                ),
                AttributeOverride(
                    name = "middleName",
                    column = Column(name = "client_middle_name")
                ),
                AttributeOverride(
                    name = "lastName",
                    column = Column(name = "client_last_name")
                ),
                AttributeOverride(name = "street", column = Column(name = "client_street")),
                AttributeOverride(name = "number", column = Column(name = "client_number")),
                AttributeOverride(name = "numberAddition", column = Column(name = "client_number_addition")),
                AttributeOverride(name = "postalCode", column = Column(name = "client_postal_code")),
                AttributeOverride(name = "town", column = Column(name = "client_town")),
                AttributeOverride(
                    name = "phoneNumber",
                    column = Column(name = "client_phone_number")
                ),
                AttributeOverride(
                    name = "emailAddress",
                    column = Column(name = "client_email_address")
                ),
            ]
    )
    val client: Client,
    @Embedded
    @AttributeOverrides(
        value = [
            AttributeOverride(name = "startTime", column = Column(name = "pickup_time_start_time")),
            AttributeOverride(name = "endTime", column = Column(name = "pickup_time_end_time"))
        ]
    )
    val pickUpTime: PickUpTime
)
