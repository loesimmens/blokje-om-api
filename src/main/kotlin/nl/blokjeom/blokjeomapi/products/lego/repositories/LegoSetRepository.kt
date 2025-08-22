package nl.blokjeom.blokjeomapi.products.lego.repositories

import nl.blokjeom.blokjeomapi.products.lego.domain.LegoSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LegoSetRepository: JpaRepository<LegoSet, String>
