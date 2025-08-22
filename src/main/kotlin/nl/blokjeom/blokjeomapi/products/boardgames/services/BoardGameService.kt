package nl.blokjeom.blokjeomapi.products.boardgames.services

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.products.boardgames.config.BoardGameConfigurationProperties
import nl.blokjeom.blokjeomapi.products.boardgames.domain.BoardGame
import nl.blokjeom.blokjeomapi.products.boardgames.mapping.toBoardGame
import nl.blokjeom.blokjeomapi.products.boardgames.repositories.BoardGameRepository
import nl.blokjeom.blokjeomapi.products.services.ProductService
import org.springframework.stereotype.Service

@Service
class BoardGameService(
    private val boardGameConfigurationProperties: BoardGameConfigurationProperties,
    private val boardGameGeekApiService: BoardGameGeekApiService,
    private val boardGameRepository: BoardGameRepository,
): ProductService() {
    val logger = KotlinLogging.logger { }

    fun getAllGames(): List<BoardGame> {
        logger.debug { "Getting all board games"}
        val ids = boardGameConfigurationProperties.gameInfo.keys.toList().ifEmpty { return emptyList() }
        val savedGames = boardGameRepository.findAllById(ids)

        if(getIdsOutOfDate(ids, savedGames).isNotEmpty()) {
            logger.info { "Getting board games from Board Game Geek API: $ids" }
            val bggGames = boardGameGeekApiService.getGamesWithIds(ids)

            val boardGames = bggGames.map { it.toBoardGame(boardGameConfigurationProperties.gameInfo) }

            logger.info { "Saving board games from API to database" }
            return boardGameRepository.saveAll(boardGames)
        }

        return savedGames
    }

    fun getOneGame(id : String) =
        boardGameRepository.findById(id)
}
