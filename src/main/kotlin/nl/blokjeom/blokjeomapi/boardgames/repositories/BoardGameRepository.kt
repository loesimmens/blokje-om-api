package nl.blokjeom.blokjeomapi.boardgames.repositories

import nl.blokjeom.blokjeomapi.orders.domain.entities.BoardGame
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardGameRepository: JpaRepository<BoardGame, String>
