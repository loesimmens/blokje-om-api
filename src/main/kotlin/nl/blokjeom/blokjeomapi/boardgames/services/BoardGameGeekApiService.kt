package nl.blokjeom.blokjeomapi.boardgames.services

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.boardgames.config.BoardGameConfigurationProperties
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class BoardGameGeekApiService(
    private val boardGameConfigurationProperties: BoardGameConfigurationProperties,
) {
    private val restTemplate = RestTemplate()
    private val logger = KotlinLogging.logger { }

    fun getOneGame(gameId: String) {
        logger.debug { "Getting board game with id $gameId" }

        val url = "https://boardgamegeek.com/xmlapi/boardgame/$gameId"

        try {
            val response: ResponseEntity<String> = restTemplate.getForEntity(url, String::class.java)
            logger. debug { response.body }
        } catch (e: Exception) {
            throw e
        }
    }
}
