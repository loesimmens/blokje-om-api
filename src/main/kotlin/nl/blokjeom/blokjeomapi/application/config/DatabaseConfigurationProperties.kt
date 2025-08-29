package nl.blokjeom.blokjeomapi.application.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "database")
class DatabaseConfigurationProperties(
    val url: String,
    val username: String,
)
