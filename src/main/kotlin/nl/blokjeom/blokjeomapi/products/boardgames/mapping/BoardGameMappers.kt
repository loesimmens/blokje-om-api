package nl.blokjeom.blokjeomapi.products.boardgames.mapping

import com.boardgamegeek.xmlapi.boardgames.Boardgame
import nl.blokjeom.blokjeomapi.products.boardgames.domain.BoardGame
import java.time.Instant

fun Boardgame.toBoardGame(setInfo: Map<String, Int>): BoardGame =
    BoardGame(
        id = objectid.toString(),
        name = name.value,
        year = yearpublished,
        age = age,
        available = false,
        description = description,
        creationTime = Instant.now(),
        imageUrl = image,
        maxPlayers = maxplayers,
        maxPlayTime = maxplaytime,
        minPlayers = minplayers,
        minPlayTime = minplaytime,
        modificationTime = Instant.now(),
        playingTime = playingtime,
        rentalPricePerWeek = setInfo[objectid.toString()]!!
    )
