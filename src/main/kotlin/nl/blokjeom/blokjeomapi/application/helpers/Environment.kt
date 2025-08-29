package nl.blokjeom.blokjeomapi.application.helpers

import nl.blokjeom.blokjeomapi.application.exceptions.BlokjeOmException

open class Environment(
    private val environmentVariableName: String
) {
    open fun getVariable() = System.getenv(environmentVariableName)
        ?: throw BlokjeOmException("Environment variable $environmentVariableName is not set")
}
