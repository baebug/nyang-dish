package com.nyang.ourkitty.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.view.InternalResourceViewResolver
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

@Configuration
@EnableWebMvc
class SwaggerConfig {

    @Bean
    fun defaultViewResolver() = InternalResourceViewResolver()

    @Bean
    fun swaggerApi(): Docket = Docket(DocumentationType.SWAGGER_2)
        .consumes(getConsumeContentTypes())
        .produces(getProduceContentTypes())
        .apiInfo(swaggerInfo())
        .securitySchemes(listOf(apiKey()))
        .securityContexts(listOf(securityContext()))
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.nyang.ourkitty"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)


    // API Key 설정
    private fun apiKey(): ApiKey {
        return ApiKey("JWT", "Authorization", "header")
    }

    // 보안 설정
    private fun securityContext(): SecurityContext {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .build()
    }

    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOf(authorizationScope)
        return listOf(SecurityReference("JWT", authorizationScopes))
    }

    private fun swaggerInfo() = ApiInfoBuilder()
        .title("OurKitty Rest API Documentation")
        .description(
            """
           SSAFY 8기 부울경 2반 3조 자율프로젝트
           
           도시와 길 고양이의 공존을 위한 길고양이 급식소 '냥그릇'
        """.trimIndent()
        )
        .version("1.0")
        .build()

    // Request 로 보낼 수 있는 문서 타입 지정
    private

    fun getConsumeContentTypes(): Set<String> {
        return setOf("multipart/form-data")
    }

    // Response 로 받을 수 있는 문서 타입 지정
    private fun getProduceContentTypes(): Set<String> {
        return setOf("application/json;charset=UTF-8")
    }
    
}