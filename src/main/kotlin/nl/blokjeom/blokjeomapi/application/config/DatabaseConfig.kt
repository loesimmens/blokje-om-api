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
    fun getDataSource(databaseConfigurationProperties: DatabaseConfigurationProperties): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        logger.debug { "Configuring DataSource with URL: ${databaseConfigurationProperties.url} and Username: ${databaseConfigurationProperties.username}" }
        dataSourceBuilder.url(databaseConfigurationProperties.url)
        dataSourceBuilder.username(databaseConfigurationProperties.username)
        dataSourceBuilder.password(EnvironmentHelper.getSecretFromFileInEnvVariable(Environment("POSTGRES_PASSWORD_FILE")))
        return dataSourceBuilder.build()
    }
}
