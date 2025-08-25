package nl.blokjeom.blokjeomapi.application.exceptions

data class BlokjeOmException(
    override val message: String,
    override val cause: Throwable? = null,
) : Exception(message, cause)
