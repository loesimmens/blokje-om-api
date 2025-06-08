package nl.blokjeom.blokjeomapi.boardgames.services

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.boardgames.TestHelper.getFileAsString
import org.junit.jupiter.api.Test

class BoardGameGeekApiServiceTest {
    private val service = BoardGameGeekApiService()

    private val logger = KotlinLogging.logger {}

    @Test
    fun mapXml() {
        val xml = getFileAsString("boardgame.xml")

        val result = service.mapXml(xml)
    }

}