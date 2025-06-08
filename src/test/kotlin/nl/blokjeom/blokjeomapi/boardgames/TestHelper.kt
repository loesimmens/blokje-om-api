package nl.blokjeom.blokjeomapi.boardgames

import org.springframework.util.ResourceUtils

object TestHelper {
    fun getFileAsString(path: String): String = ResourceUtils.getFile("classpath:$path").readText()
}