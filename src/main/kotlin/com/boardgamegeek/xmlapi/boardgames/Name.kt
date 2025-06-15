package com.boardgamegeek.xmlapi.boardgames

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText

data class Name(
    @JacksonXmlProperty(isAttribute = true) val isPrimary: Boolean,
    @JacksonXmlProperty(isAttribute = true) val sortindex: Int = 0,
)  {
    @JacksonXmlText lateinit var value: String private set
}
