package com.boardgamegeek.xmlapi.boardgames

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText

data class Link(
    @JacksonXmlProperty(isAttribute = true) val objectid: Int = 0
) {
    @JacksonXmlText lateinit var value: String private set
}
