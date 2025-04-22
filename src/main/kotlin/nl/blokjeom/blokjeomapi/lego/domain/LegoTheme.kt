package nl.blokjeom.blokjeomapi.lego.domain

enum class LegoTheme(val themeId: Int) {
    NO_THEME(0);

    companion object {
        fun fromId(themeId: Int): LegoTheme? =
            LegoTheme.values().firstOrNull { it.themeId == themeId }
    }
}
