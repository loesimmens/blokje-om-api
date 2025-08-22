package nl.blokjeom.blokjeomapi.products.lego.mapping

import nl.blokjeom.blokjeomapi.products.domain.ProductType
import nl.blokjeom.blokjeomapi.products.lego.domain.LegoTheme
import nl.blokjeom.blokjeomapi.products.lego.domain.LegoSet
import nl.blokjeom.blokjeomapi.products.lego.dto.RBLegoSet
import java.time.Instant

fun RBLegoSet.toLegoSet(setInfo: Map<String, Int>) =
    LegoSet(
        id = this.set_num,
        productType = ProductType.LEGO_SET,
        name = this.name,
        year = this.year,
        theme = LegoTheme.fromId(this.theme_id) ?: LegoTheme.NO_THEME,
        numberOfParts = this.num_parts,
        creationTime = Instant.now(),
        modificationTime = Instant.now(),
        imageUrl = this.set_img_url,
        rentalPricePerWeek = setInfo[this.set_num]!!,
        buildTogether = false,
        available = true,
        description = ""
    )

