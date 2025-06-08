package nl.blokjeom.blokjeomapi.boardgames.services

import com.boardgamegeek.xmlapi.boardgames.Boardgame
import com.boardgamegeek.xmlapi.boardgames.Boardgames
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset


@Service
class BoardGameGeekApiService {
    private val restTemplate = RestTemplate()
    private val logger = KotlinLogging.logger { }
    private val xmlMapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    init {
        restTemplate.messageConverters
            .add(0, StringHttpMessageConverter(Charset.forName("UTF-16")))
    }

    fun getOneGame(gameId: String): Boardgame {
        logger.debug { "Getting board game with id $gameId from BGG API" }

        val url = "https://boardgamegeek.com/xmlapi/boardgame/$gameId"

        val headers = HttpHeaders()
        headers[HttpHeaders.ACCEPT] = MediaType.APPLICATION_XML_VALUE
        val httpEntity = HttpEntity<String>(headers)

        try {
            val response: ResponseEntity<String> = restTemplate.exchange(
                url, HttpMethod.GET, httpEntity, String::class.java
            )
            val boardgames = mapXml(response.body)
            return boardgames.boardgame.first()
        } catch (e: Exception) {
            logger.error(e) { "Failed to get boardgame with id $gameId" }
            throw e
        }
    }

    fun mapXml(xml: String?): Boardgames =
        xmlMapper.readValue(xml, Boardgames::class.java)
}
