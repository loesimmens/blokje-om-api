package com.boardgamegeek.xmlapi.boardgames

data class Poll(
    val results: Results? = null,
    val name: String? = null,
    val title: String? = null,
    val totalvotes: Short = 0
) {
    data class Results(
        val result: MutableList<Result> = mutableListOf(),
        val numplayers: String? = null,
    ) {
        data class Result(
            val value: String? = null,
            val numvotes: Short = 0,
            val level: Short? = null
        )
    }
}