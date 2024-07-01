package com.spring_boot_security_155.configuration;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Spring Boot Security 155")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spring_boot_security_155.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring boot security 155")
                .description("MySql database authentication")
                .version("1.0")
                .build();
    }
    @EventListener(ApplicationContext.class)
    public void displayUrl(){
        System.out.println("Swagger-ui url: http://localhost:7070/swagger-ui/index.html");
    }
}
