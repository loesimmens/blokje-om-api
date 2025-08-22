package nl.blokjeom.blokjeomapi.products.boardgames.repositories

import nl.blokjeom.blokjeomapi.products.boardgames.domain.BoardGame
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardGameRepository: JpaRepository<BoardGame, String>
