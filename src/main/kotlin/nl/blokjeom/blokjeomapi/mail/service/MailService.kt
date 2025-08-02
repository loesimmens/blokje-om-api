package nl.blokjeom.blokjeomapi.mail.service

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.mail.MessagingException
import nl.blokjeom.blokjeomapi.mail.config.MailConfigurationProperties
import org.springframework.core.io.Resource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

@Service
class MailService(
    private val emailSender: JavaMailSender,
    private val thymeleafTemplateEngine: SpringTemplateEngine,
    private val mailConfigurationProperties: MailConfigurationProperties
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

    @Throws(MessagingException::class)
    fun sendMessageUsingThymeleafTemplate(
        to: String,
        subject: String,
        templatePath: String,
        templateModel: MutableMap<String, Any>,
        image: Resource
    ) {
        logger.info { "Sending Thymeleaf template email to: $to" }

        val thymeleafContext = Context()
        thymeleafContext.setVariables(templateModel)
        val htmlBody = thymeleafTemplateEngine.process(templatePath, thymeleafContext)

        sendHtmlMessage(to, subject, htmlBody, image)
    }

    @Throws(MessagingException::class)
    private fun sendHtmlMessage(to: String, subject: String, htmlBody: String, image: Resource) {
        logger.info { "Sending html template email to: $to" }

        val message = emailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")

        helper.setFrom("noreply@blokje-om.nl")
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlBody, true)
        helper.addInline("logo.png", image)

        message.saveChanges()

        emailSender.send(message)
    }
}
