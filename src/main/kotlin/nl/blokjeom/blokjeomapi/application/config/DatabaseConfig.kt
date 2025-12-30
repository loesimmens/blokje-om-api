package nl.blokjeom.blokjeomapi.application.config

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.application.helpers.Environment
import nl.blokjeom.blokjeomapi.application.helpers.EnvironmentHelper
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatabaseConfig {
    private val logger = KotlinLogging.logger {  }

    @Bean
    fun getDataSource(properties: DatabaseConfigurationProperties): DataSource {
        val host = EnvironmentHelper.getSecretFromFileInEnvVariable(Environment("DB_HOST_FILE"))
        val url = "jdbc:postgresql://$host:${properties.port}/${properties.name}"

        logger.debug { "Configuring DataSource with URL: $url and username: ${properties.username}" }

        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.url(url)
        dataSourceBuilder.username(properties.username)
        dataSourceBuilder.password(EnvironmentHelper.getSecretFromFileInEnvVariable(Environment("POSTGRES_PASSWORD_FILE")))
        return dataSourceBuilder.build()
    }
}
