package nl.blokjeom.blokjeomapi.orders.services

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailService(
    private val emailSender: JavaMailSender
) {
    val logger = KotlinLogging.logger {}

    fun sendSimpleMessage(to: String, subject: String, text: String) {
        logger.info { "Sending email to: $to" }

        val message = SimpleMailMessage()
        message.from = "noreply@blokje-om.nl"
        message.setTo(to)
        message.subject = subject
        message.text = text
        emailSender.send(message)
    }
}
