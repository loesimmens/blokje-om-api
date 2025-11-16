package nl.blokjeom.blokjeomapi.application.server

import nl.blokjeom.blokjeomapi.application.helpers.Environment
import nl.blokjeom.blokjeomapi.application.helpers.EnvironmentHelper
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServerConfig {
    @Bean
    fun getServerProperties(): ServerProperties {
        val serverProperties = ServerProperties()
        serverProperties.port = 443
        serverProperties.ssl.keyStore = "/usr/share/keystore.p12"
        serverProperties.ssl.keyStorePassword = EnvironmentHelper.getSecretFromFileInEnvVariable(Environment("KEYSTORE_PASSWORD_FILE"))
        serverProperties.ssl.keyStoreType = "PKCS12"
        serverProperties.ssl.keyAlias = "tomcat"
        return serverProperties
    }
}
