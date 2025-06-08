package nl.blokjeom.blokjeomapi.boardgames.services

import com.boardgamegeek.xmlapi.boardgames.Boardgame
import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.boardgames.config.BoardGameConfigurationProperties
import nl.blokjeom.blokjeomapi.boardgames.domain.entities.BoardGame
import nl.blokjeom.blokjeomapi.boardgames.repositories.BoardGameRepository
import org.springframework.stereotype.Service

@Service
class BoardGameService(
    private val boardGameConfigurationProperties: BoardGameConfigurationProperties,
    private val boardGameGeekApiService: BoardGameGeekApiService,
    private val boardGameRepository: BoardGameRepository,
) {
    val logger = KotlinLogging.logger { }

    fun getAllGames(): List<BoardGame> {
        logger.debug { "Getting all board games"}
        val ids = boardGameConfigurationProperties.gameInfo.keys.toList().ifEmpty { return emptyList() }
        return emptyList()
    }

    fun getOneGame(id : String): Boardgame {
        logger.debug { "Getting game with id: $id "}
        val game = boardGameGeekApiService.getOneGame(id)
        return game
    }
}
