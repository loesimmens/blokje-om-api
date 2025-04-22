package nl.blokjeom.blokjeomapi.lego.mapping

import nl.blokjeom.blokjeomapi.lego.domain.LegoTheme
import nl.blokjeom.blokjeomapi.lego.domain.entities.LegoSet
import nl.blokjeom.blokjeomapi.lego.dto.RBLegoSet
import java.time.Instant

fun RBLegoSet.toLegoSet() =
    LegoSet(
        id = this.set_num,
        name = this.name,
        year = this.year,
        theme = LegoTheme.fromId(this.theme_id) ?: LegoTheme.NO_THEME,
        numberOfParts = this.num_parts,
        creationTime = Instant.now(),
        modificationTime = Instant.now(),
        imageUrl = this.set_img_url
    )

