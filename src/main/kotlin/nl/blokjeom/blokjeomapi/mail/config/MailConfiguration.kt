package nl.blokjeom.blokjeomapi.mail.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.thymeleaf.templateresolver.ITemplateResolver


@Configuration
class MailConfiguration(
    private val mailConfigurationProperties: MailConfigurationProperties
) {
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
