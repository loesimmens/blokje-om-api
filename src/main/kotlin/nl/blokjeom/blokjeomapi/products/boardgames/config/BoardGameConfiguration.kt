package nl.blokjeom.blokjeomapi.products.boardgames.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
class BoardGameConfiguration

@ConfigurationProperties("boardgames")
class BoardGameConfigurationProperties(
    val boardGameGeekApi: BoardGameGeekApiConfigurationProperties,
    val gameInfo: Map<String, Int>,
)
