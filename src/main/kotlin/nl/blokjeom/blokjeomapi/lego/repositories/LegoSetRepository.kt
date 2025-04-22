package nl.blokjeom.blokjeomapi.lego.repositories

import nl.blokjeom.blokjeomapi.lego.domain.entities.LegoSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LegoSetRepository: JpaRepository<LegoSet, String>
