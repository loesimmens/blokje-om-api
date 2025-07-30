package nl.blokjeom.blokjeomapi.mail.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "mail")
data class MailConfigurationProperties(
    val blokjeOmEmail: String
)
