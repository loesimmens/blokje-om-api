package nl.blokjeom.blokjeomapi

import nl.blokjeom.blokjeomapi.orders.domain.entities.ProductOrder
import nl.blokjeom.blokjeomapi.orders.domain.valueobjects.Client
import nl.blokjeom.blokjeomapi.orders.domain.valueobjects.PickUpTime
import nl.blokjeom.blokjeomapi.products.domain.ProductType
import nl.blokjeom.blokjeomapi.products.lego.domain.LegoSet
import nl.blokjeom.blokjeomapi.products.lego.domain.LegoTheme
import org.springframework.core.io.ClassPathResource
import java.time.Instant

object TestHelper {
    const val PRODUCT_ID = "1234"
    const val PRODUCT_NAME = "Test Product"
    const val RENTAL_PRICE_PER_WEEK_IN_CENTS = 1000
    const val RENTAL_PRICE_PER_WEEK_IN_EUROS = 10
    const val IMAGE_URL = "http://example.com/image.jpg"

    const val CLIENT_MAIL_TEMPLATE_FILE_NAME = "client-email"
    const val BLOKJE_OM_MAIL_TEMPLATE_FILE_NAME = "blokje-om-email"

    val startTime: Instant = Instant.parse("2025-08-17T14:00:00Z") // 17 August 2025, 16:00 in Amsterdam time
    val endTime: Instant = startTime.plusSeconds(604800) // 1 week later

    const val FORMATTED_START_TIME = "zondag 17 augustus 2025 16:00"
    const val FORMATTED_END_TIME = "zondag 24 augustus 2025 16:00"

    const val FIRST_NAME = "John"
    const val MIDDLE_NAME = "Von"
    const val LAST_NAME = "Doe"
    const val CLIENT_NAME = "$FIRST_NAME $MIDDLE_NAME $LAST_NAME"
    const val STREET = "Main St"
    const val NUMBER = "123"
    const val NUMBER_ADDITION = "A"
    const val STREET_LINE = "$STREET $NUMBER$NUMBER_ADDITION"
    const val POSTAL_CODE = "1234AB"
    const val TOWN = "Test City"
    const val PHONE_NUMBER = "+31612345678"

    const val CLIENT_EMAIL_ADDRESS = "john@tc.nl"
    const val BLOKJE_OM_EMAIL_ADDRESS = "info@blokje-om.nl"

    val LOGO_PATH = ClassPathResource("classpath:static/images/blokje-om-logo.png")
    
    val legoSet = LegoSet(
        id = PRODUCT_ID,
        productType = ProductType.LEGO_SET,
        name = PRODUCT_NAME,
        year = 2023,
        theme = LegoTheme.NO_THEME,
        numberOfParts = 1000,
        creationTime = Instant.now(),
        modificationTime = Instant.now(),
        imageUrl = IMAGE_URL,
        rentalPricePerWeek = RENTAL_PRICE_PER_WEEK_IN_CENTS,
        buildTogether = false,
        available = true,
        description = "A test Lego set for unit testing."
    )

    val productOrder = ProductOrder(
        productId = PRODUCT_ID,
        productType = ProductType.LEGO_SET,
        pickUpTime = PickUpTime(
            startTime = startTime,
            endTime = endTime
        ),
        client = Client(
            firstName = FIRST_NAME,
            middleName = MIDDLE_NAME,
            lastName = LAST_NAME,
            street = STREET,
            number = NUMBER,
            numberAddition = NUMBER_ADDITION,
            postalCode = POSTAL_CODE,
            town = TOWN,
            phoneNumber = PHONE_NUMBER,
            emailAddress = CLIENT_EMAIL_ADDRESS,
        )
    )
    
    val mailTemplateModel = mutableMapOf<String, Any>().apply {
        this["clientFirstName"] = FIRST_NAME
        this["clientName"] = CLIENT_NAME
        this["clientStreetLine"] = STREET_LINE
        this["clientPostalCode"] = POSTAL_CODE
        this["clientTown"] = TOWN
        this["clientEmailAddress"] = CLIENT_EMAIL_ADDRESS
        this["clientPhoneNumber"] = PHONE_NUMBER
        this["productName"] = PRODUCT_NAME
        this["productId"] = PRODUCT_ID
        this["startDate"] = FORMATTED_START_TIME
        this["endDate"] = FORMATTED_END_TIME
        this["price"] = RENTAL_PRICE_PER_WEEK_IN_EUROS
        this["productImage"] = IMAGE_URL
    }
}