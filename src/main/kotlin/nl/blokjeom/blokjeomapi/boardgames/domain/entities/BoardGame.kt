package nl.blokjeom.blokjeomapi.boardgames.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class BoardGame(
    @Id val id: String,
)
