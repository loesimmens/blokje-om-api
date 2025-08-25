package nl.blokjeom.blokjeomapi.application.helpers

import nl.blokjeom.blokjeomapi.application.exceptions.BlokjeOmException
import org.springframework.util.ResourceUtils

object EnvironmentHelper {
    fun getSecretFromFileInEnvVariable(environment: Environment): String {
        try {
            val path = environment.getVariable()
            return ResourceUtils.getFile(path).readText().trim()
        } catch (e: Exception) {
            throw BlokjeOmException("Failed to read database password from file", e)
        }
    }
}
