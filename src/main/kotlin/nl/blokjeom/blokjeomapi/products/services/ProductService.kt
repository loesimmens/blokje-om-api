package nl.blokjeom.blokjeomapi.products.services

import nl.blokjeom.blokjeomapi.products.domain.Product
import java.time.Duration
import java.time.Instant

abstract class ProductService {
    fun getIdsOutOfDate(setIds: List<String>, savedProducts: List<Product>): List<String> {
        val idsNotSaved = setIds
            .filter { !idAlreadySaved(savedProducts, it) }

        if (idsNotSaved.isNotEmpty()) {
            return idsNotSaved
        }

        return savedProducts
            .filter { it.modificationTime < Instant.now().minus(Duration.ofDays(7)) }
            .map { it.id }
    }

    private fun idAlreadySaved(
        savedProducts: List<Product>,
        it: String
    ) = savedProducts.map { savedProduct -> savedProduct.id }.contains(it)

}