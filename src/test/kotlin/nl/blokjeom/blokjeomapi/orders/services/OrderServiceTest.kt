package nl.blokjeom.blokjeomapi.orders.services

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import io.mockk.verifySequence
import nl.blokjeom.blokjeomapi.TestHelper
import nl.blokjeom.blokjeomapi.TestHelper.BLOKJE_OM_EMAIL_ADDRESS
import nl.blokjeom.blokjeomapi.TestHelper.BLOKJE_OM_MAIL_TEMPLATE_FILE_NAME
import nl.blokjeom.blokjeomapi.TestHelper.CLIENT_EMAIL_ADDRESS
import nl.blokjeom.blokjeomapi.TestHelper.CLIENT_MAIL_TEMPLATE_FILE_NAME
import nl.blokjeom.blokjeomapi.TestHelper.LOGO_PATH
import nl.blokjeom.blokjeomapi.TestHelper.PRODUCT_ID
import nl.blokjeom.blokjeomapi.TestHelper.mailTemplateModel
import nl.blokjeom.blokjeomapi.TestHelper.productOrder
import nl.blokjeom.blokjeomapi.mail.config.MailConfigurationProperties
import nl.blokjeom.blokjeomapi.mail.service.MailService
import nl.blokjeom.blokjeomapi.orders.repositories.OrderRepository
import nl.blokjeom.blokjeomapi.orders.services.OrderService.Companion.BEDANKT
import nl.blokjeom.blokjeomapi.products.boardgames.services.BoardGameService
import nl.blokjeom.blokjeomapi.products.lego.services.LegoService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Optional
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class OrderServiceTest {
    @InjectMockKs private lateinit var orderService: OrderService
    @MockK private lateinit var orderRepository: OrderRepository
    @MockK private lateinit var mailService: MailService
    @MockK private lateinit var mailConfigurationProperties: MailConfigurationProperties
    @MockK private lateinit var legoService: LegoService
    @MockK private lateinit var boardGameService: BoardGameService

    @Test
    fun order() {
        val order = productOrder
        every {
            mailConfigurationProperties.clientMailTemplateFileName
        } returns CLIENT_MAIL_TEMPLATE_FILE_NAME
        every {
            mailService.sendMessageUsingThymeleafTemplate(
                any<String>(),
                any<String>(),
                any<String>(),
                any(),
                any()
            )
        } returns Unit
        every {
            legoService.getOneSet(PRODUCT_ID)
        } returns Optional.of(TestHelper.legoSet)
        every {
            mailConfigurationProperties.logoPath
        } returns LOGO_PATH
        every {
            mailConfigurationProperties.blokjeOmEmail
        } returns BLOKJE_OM_EMAIL_ADDRESS
        every {
            mailConfigurationProperties.blokjeOmMailTemplateFileName
        } returns BLOKJE_OM_MAIL_TEMPLATE_FILE_NAME
        every {
            orderRepository.save(order)
        } returns order

        val result = orderService.order(order)

        assertThat(result).isEqualTo(order)

        verifySequence {
            mailService.sendMessageUsingThymeleafTemplate(
                CLIENT_EMAIL_ADDRESS,
                BEDANKT,
                CLIENT_MAIL_TEMPLATE_FILE_NAME,
                mailTemplateModel,
                LOGO_PATH
            )
            mailService.sendMessageUsingThymeleafTemplate(
                BLOKJE_OM_EMAIL_ADDRESS,
                "Besteld: product ${order.productId}, door ${order.client.firstName} ${order.client.middleName} ${order.client.lastName}",
                BLOKJE_OM_MAIL_TEMPLATE_FILE_NAME,
                mailTemplateModel,
                LOGO_PATH
            )
        }
        verify { mailConfigurationProperties.clientMailTemplateFileName }
        verify(exactly = 2) { mailConfigurationProperties.logoPath }
        verify { mailConfigurationProperties.blokjeOmMailTemplateFileName }
        verify { legoService.getOneSet(PRODUCT_ID) }
        verify { orderRepository.save(productOrder) }
    }
}