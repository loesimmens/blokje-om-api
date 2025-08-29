package nl.blokjeom.blokjeomapi.mail.config

import nl.blokjeom.blokjeomapi.application.helpers.EnvironmentHelper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.thymeleaf.templateresolver.ITemplateResolver

@Configuration
class MailConfiguration(
    private val mailConfigurationProperties: MailConfigurationProperties
) {
    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "mail.antagonist.nl"
        mailSender.port = 587
        mailSender.username = "info@blokje-om.nl"
        mailSender.password = EnvironmentHelper.getSecretFromFileInEnvVariable("EMAIL_PASSWORD_FILE")

        val mailProperties = mailSender.javaMailProperties
        mailProperties["mail.smtp.auth"] = "true"
        mailProperties["mail.smtp.starttls.enable"] = "true"
        return mailSender
    }

    @Qualifier("thymeleafTemplateResolver")
    @Bean
    fun thymeleafTemplateResolver(): ITemplateResolver {
        val templateResolver = ClassLoaderTemplateResolver()
        templateResolver.prefix = mailConfigurationProperties.templatesFolder
        templateResolver.suffix = ".html"
        templateResolver.setTemplateMode("HTML")
        templateResolver.characterEncoding = "UTF-8"
        return templateResolver
    }

    @Bean
    fun thymeleafTemplateEngine(thymeleafTemplateResolver: ITemplateResolver): SpringTemplateEngine {
        val templateEngine = SpringTemplateEngine()
        templateEngine.setTemplateResolver(thymeleafTemplateResolver)
        return templateEngine
    }
}
