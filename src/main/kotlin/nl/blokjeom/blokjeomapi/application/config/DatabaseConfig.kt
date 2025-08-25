package nl.blokjeom.blokjeomapi.application.config

import nl.blokjeom.blokjeomapi.application.helpers.Environment
import nl.blokjeom.blokjeomapi.application.helpers.EnvironmentHelper
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatabaseConfig {
    @Bean
    fun getDataSource(): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.h2.Driver")
        dataSourceBuilder.url("jdbc:h2:mem:test")
        dataSourceBuilder.username("bo_admin")
        dataSourceBuilder.password(EnvironmentHelper.getSecretFromFileInEnvVariable(Environment("POSTGRES_PASSWORD_FILE")))
        return dataSourceBuilder.build()
    }
}
