package com.boardgamegeek.xmlapi.boardgames

data class Boardgames(
    var boardgame: MutableList<Boardgame> = mutableListOf(),
    var termsofuse: String,
)
