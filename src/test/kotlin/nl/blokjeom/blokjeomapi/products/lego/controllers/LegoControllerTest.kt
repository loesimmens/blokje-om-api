package nl.blokjeom.blokjeomapi.products.lego.controllers

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import nl.blokjeom.blokjeomapi.TestHelper.legoSet
import nl.blokjeom.blokjeomapi.products.lego.services.LegoService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Optional

@ExtendWith(MockKExtension::class)
class LegoControllerTest {
    @InjectMockKs private lateinit var legoController: LegoController
    @MockK private lateinit var legoService: LegoService

    @Test
    fun getAllLegoSets() {

    }

    @Test
    fun getLegoSet() {
        val id = "1234"
        every {
            legoService.getOneSet(id)
        } returns Optional.of(legoSet)

        val result = legoController.getLegoSet(id)

        assertThat(result).isEqualTo(legoSet)

        verify { legoService.getOneSet(id) }
    }
}
