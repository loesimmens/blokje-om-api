package nl.blokjeom.blokjeomapi.products.lego.services

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.blokjeom.blokjeomapi.products.lego.config.LegoConfigurationProperties
import nl.blokjeom.blokjeomapi.products.lego.domain.LegoSet
import nl.blokjeom.blokjeomapi.products.lego.mapping.toLegoSet
import nl.blokjeom.blokjeomapi.products.lego.repositories.LegoSetRepository
import nl.blokjeom.blokjeomapi.products.services.ProductService
import org.springframework.stereotype.Service

@Service
class LegoService(
    private val legoConfigurationProperties: LegoConfigurationProperties,
    private val rebrickableApiService: RebrickableApiService,
    private val legoSetRepository: LegoSetRepository,
): ProductService() {
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

    fun getOneSet(id: String) =
        legoSetRepository.findById(id)
}
