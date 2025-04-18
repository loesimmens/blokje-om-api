package nl.blokjeom.blokjeomapi.lego.controllers

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.lego.dto.LegoSet
import nl.blokjeom.blokjeomapi.lego.services.LegoService
import nl.blokjeom.blokjeomapi.lego.services.RebrickableApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClientException

@RestController
@RequestMapping("/lego")
class LegoController(
    private val rebrickableApiService: RebrickableApiService,
    private val legoService: LegoService
) {
    private val logger = KotlinLogging.logger { }

    @GetMapping
    fun getAllSets(): List<LegoSet> {
        try {
            return rebrickableApiService.getAllSets()
        } catch (e: RestClientException) {
            logger.error(e) { "Error getting lego sets: ${e.message}" }
            throw e
        }
    }
}
