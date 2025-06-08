package nl.blokjeom.blokjeomapi.boardgames.services

import nl.blokjeom.blokjeomapi.boardgames.TestHelper.getFileAsString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardGameGeekApiServiceTest {
    private val service = BoardGameGeekApiService()

    @Test
    fun mapXml() {
        val xml = getFileAsString("boardgame.xml")

        val result = service.mapXml(xml)

        assertThat(result.boardgame.first()).isNotNull
    }

}