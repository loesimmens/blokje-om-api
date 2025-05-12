package nl.blokjeom.blokjeomapi.boardgames.services

import com.boardgamegeek.xmlapi.boardgames.Boardgame
import com.boardgamegeek.xmlapi.boardgames.Boardgames
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
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
    private val xmlMapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)


    fun getOneGame(gameId: String): Boardgame {
        logger.debug { "Getting board game with id $gameId from BGG API" }

        val url = "https://boardgamegeek.com/xmlapi/boardgame/$gameId"

        try {
            val response: ResponseEntity<String> =
                restTemplate.getForEntity(url, String::class.java)
            logger.debug { "BGG API reponse: ${response.body}" }
            val boardgames = xmlMapper.readValue(response.body, Boardgames::class.java)
            return boardgames.boardgame.first()
        } catch (e: Exception) {
            logger.error(e) { "Failed to get boardgame with id $gameId" }
            throw e
        }
    }
}
