package com.boardgamegeek.xmlapi.boardgames

import java.math.BigInteger

data class Name(
    val value: String? = null,
    val isPrimary: Boolean = false,
    val sortindex: BigInteger? = null
)
