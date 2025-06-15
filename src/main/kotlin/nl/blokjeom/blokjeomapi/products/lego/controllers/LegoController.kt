package nl.blokjeom.blokjeomapi.products.lego.controllers

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.products.lego.domain.LegoSet
import nl.blokjeom.blokjeomapi.products.lego.services.LegoService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClientException

@RestController
@RequestMapping("/lego")
@CrossOrigin(origins = ["http://localhost:4200"]) // todo remove
class LegoController(
    private val legoService: LegoService
) {
    private val logger = KotlinLogging.logger { }

    @GetMapping
    fun getAllLegoSets(): List<LegoSet> {
        try {
            return legoService.getAllSets()
        } catch (e: RestClientException) {
            logger.error(e) { "Error getting lego sets: ${e.message}" }
            throw e
        }
    }

    @GetMapping("/{id}")
    fun getLegoSet(@PathVariable id: String): LegoSet {
        try {
            return legoService.getOneSet(id).orElseThrow()
        } catch (e: Exception) {
            logger.error(e) { "Error getting lego set: $id" }
            throw e
        }
    }
}
