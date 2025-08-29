package nl.blokjeom.blokjeomapi.application.helpers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EnvironmentHelperTest {
    companion object {
        const val SECRET = "extremelySECRETbeCAUTIOU5!?!?!?!"
        const val PATH = "src/test/resources/test_secret"
    }

    class MockEnvironment : Environment("MOCK_ENV_VAR") {
        override fun getVariable() = PATH
    }

    @Test
    fun getSecretFromFileInEnvVariable() {
        val result = EnvironmentHelper.getSecretFromFileInEnvVariable(MockEnvironment())

        assertThat(result).isEqualTo(SECRET)
    }
}
