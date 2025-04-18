package nl.blokjeom.blokjeomapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@ConfigurationPropertiesScan
@SpringBootApplication
class BlokjeOmApiApplication

fun main(args: Array<String>) {
	runApplication<BlokjeOmApiApplication>(*args)
}
