package nl.blokjeom.blokjeomapi.boardgames.services

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import nl.blokjeom.blokjeomapi.boardgames.TestHelper.getFileAsString
import nl.blokjeom.blokjeomapi.products.boardgames.config.BoardGameConfigurationProperties
import nl.blokjeom.blokjeomapi.products.boardgames.services.BoardGameGeekApiService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BoardGameGeekApiServiceTest {
    @InjectMockKs private lateinit var service: BoardGameGeekApiService
    @MockK private lateinit var boardGameConfigurationProperties: BoardGameConfigurationProperties

    @Test
    fun mapXml() {
        val xml = getFileAsString("boardgame.xml")

        val result = service.mapXml(xml)

        assertThat(result.boardgame.first()).isNotNull
    }

}