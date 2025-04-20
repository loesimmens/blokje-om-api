package nl.blokjeom.blokjeomapi.lego.services

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.lego.config.LegoConfigurationProperties
import nl.blokjeom.blokjeomapi.lego.dto.RBLegoSet
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Service
class RebrickableApiService(
    private val legoConfigurationProperties: LegoConfigurationProperties
) {
    private val restTemplate = RestTemplate()
    private val logger = KotlinLogging.logger {}

    fun getAllSets(): List<RBLegoSet> {
        val setIds = legoConfigurationProperties.setIds.ifEmpty { return emptyList() }
        val sets = setIds.mapNotNull { getOneSet(it) }
        return sets
    }

    fun getOneSet(setId: String): RBLegoSet? {
        logger.debug { "Getting lego set $setId" }
        val key = legoConfigurationProperties.rebrickableApi.key
        val headers = HttpHeaders().apply { set(HttpHeaders.AUTHORIZATION, "key $key") }
        val request = HttpEntity<RBLegoSet>(headers)
        val url = "${legoConfigurationProperties.rebrickableApi.url}/$setId"

        try {
            val response = restTemplate.exchange(url, HttpMethod.GET, request, RBLegoSet::class.java)
            if(response.body == null) {
                logger.warn { "Response body for set $setId is null" }
            }
            return response.body
        } catch (e: RestClientException) {
            throw e
        }
    }
}