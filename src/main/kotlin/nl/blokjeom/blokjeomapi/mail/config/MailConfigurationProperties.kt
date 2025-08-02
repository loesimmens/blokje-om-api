package nl.blokjeom.blokjeomapi.mail.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.core.io.Resource

@ConfigurationProperties(prefix = "mail")
data class MailConfigurationProperties(
    val blokjeOmEmail: String,
    val templatesFolder: String,
    val clientMailTemplateFileName: String,
    val blokjeOmMailTemplateFileName: String,
    val logoPath: Resource,
)
