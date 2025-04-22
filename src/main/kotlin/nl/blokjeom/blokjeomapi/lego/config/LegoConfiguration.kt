package nl.blokjeom.blokjeomapi.lego.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
class LegoConfiguration

@ConfigurationProperties("lego")
class LegoConfigurationProperties(
    val rebrickableApi: RebrickableApiConfigurationProperties,
    val setInfo: Map<String, Int>,
)
