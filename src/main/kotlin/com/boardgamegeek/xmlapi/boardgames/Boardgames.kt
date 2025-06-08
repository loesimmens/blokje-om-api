package com.boardgamegeek.xmlapi.boardgames

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement
data class Boardgames(
    var boardgame: MutableList<Boardgame> = mutableListOf(),
    var termsofuse: String,
)
