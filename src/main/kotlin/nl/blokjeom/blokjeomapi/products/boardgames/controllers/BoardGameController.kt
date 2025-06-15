package nl.blokjeom.blokjeomapi.products.boardgames.controllers

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.products.boardgames.domain.BoardGame
import nl.blokjeom.blokjeomapi.products.boardgames.services.BoardGameService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClientException

@RestController
@RequestMapping("/boardgames")
@CrossOrigin(origins = ["http://localhost:4200"]) // todo remove
class BoardGameController (
    private val boardGameService: BoardGameService
) {
    private val logger = KotlinLogging.logger { }

    @GetMapping
    fun getAllBoardGames(): List<BoardGame> {
        try {
            return boardGameService.getAllGames()
        } catch (e: RestClientException) {
            logger.error(e) { "Error getting board games: ${e.message}" }
            throw e
        }
    }

    @GetMapping("/{id}")
    fun getOneBoardGame(@PathVariable id : String): BoardGame {
        try {
            return boardGameService.getOneGame(id).orElseThrow()
        } catch (e: RestClientException) {
            logger.error(e) { "Error getting board game with id: $id. ${e.message}" }
            throw e
        }
    }
}
