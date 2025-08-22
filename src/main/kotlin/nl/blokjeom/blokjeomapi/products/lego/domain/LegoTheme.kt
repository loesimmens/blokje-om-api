package nl.blokjeom.blokjeomapi.products.lego.domain

enum class LegoTheme(val themeId: Int) {
    NO_THEME(0);

    companion object {
        fun fromId(themeId: Int): LegoTheme? =
            values().firstOrNull { it.themeId == themeId }
    }
}
