package nl.blokjeom.blokjeomapi.lego.services

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.lego.config.LegoConfigurationProperties
import nl.blokjeom.blokjeomapi.lego.domain.entities.LegoSet
import nl.blokjeom.blokjeomapi.lego.mapping.toLegoSet
import nl.blokjeom.blokjeomapi.lego.repositories.LegoSetRepository
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant

@Service
class LegoService(
    private val legoConfigurationProperties: LegoConfigurationProperties,
    private val rebrickableApiService: RebrickableApiService,
    private val legoSetRepository: LegoSetRepository,
) {
    val logger = KotlinLogging.logger { }

    fun getAllSets(): List<LegoSet> {
        logger.debug { "Getting all lego sets" }
        val ids = legoConfigurationProperties.setInfo.keys.toList().ifEmpty { return emptyList() }
        val savedSets = legoSetRepository.findAllById(ids)

        if(getIdsOutOfDate(ids, savedSets).isNotEmpty()) {
            logger.info { "Getting lego sets from Rebrickable API: $ids"}
            val rebrickableLegoSets = rebrickableApiService.getSetsWithIds(ids)
            val legoSets = rebrickableLegoSets.map { it.toLegoSet(legoConfigurationProperties.setInfo) }
            logger.info { "Saving lego sets from API to database"}
            return legoSetRepository.saveAll(legoSets)
        }
        return savedSets
    }

    private fun getIdsOutOfDate(setIds: List<String>, savedSets: List<LegoSet>): List<String> {
        val setIdsNotSaved = setIds
            .filter { !idAlreadySaved(savedSets, it) }

        if (setIdsNotSaved.isNotEmpty()) {
            return setIdsNotSaved
        }

        return savedSets
            .filter { it.modificationTime < Instant.now().minus(Duration.ofDays(7)) }
            .map { it.id }
    }

    private fun idAlreadySaved(
        savedSets: List<LegoSet>,
        it: String
    ) = savedSets.map { savedSet -> savedSet.id }.contains(it)
}
