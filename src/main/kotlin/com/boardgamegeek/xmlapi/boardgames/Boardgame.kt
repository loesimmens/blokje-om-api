package com.boardgamegeek.xmlapi.boardgames

data class Boardgame (
    val yearpublished: String,
    val minplayers: Int,
    val maxplayers: Int,
    val playingtime: Int,
    val minplaytime: Int,
    val maxplaytime: Int,
    val age: Int,
    val name: Name,
    val description: Description,
    val thumbnail: String,
    val image: String,
    val boardgamepublisher: MutableList<Boardgamepublisher> = mutableListOf(),
    val boardgamefamily: Boardgamefamily,
    val boardgamecategory: MutableList<Boardgamecategory> = mutableListOf(),
    val boardgamedesigner: Boardgamedesigner,
    val boardgameartist: Boardgameartist,
    val boardgameexpansion: Boardgameexpansion,
    val poll: Poll,
    val objectid: Int = 0)
