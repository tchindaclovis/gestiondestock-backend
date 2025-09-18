package com.tchindaClovis.gestiondestock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import static com.tchindaClovis.gestiondestock.utils.Constants.APP_ROOT;

@Configuration
public class SwaggerConfiguration {

    /**
     * Configuration des informations générales de l'API
     */
    @Bean
    @Primary
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion de stock REST API")
                        .description("Gestion de stock API documentation")
                        .version("v1"));
    }

    /**
     * Définition d’un groupe d’API (équivalent de Docket dans Springfox)
     */
    @Bean
    public GroupedOpenApi gestionStockApi() {
        return GroupedOpenApi.builder()
                .group("REST API V1")
                .packagesToScan("com.tchindaClovis.gestiondestock.controller")
                .pathsToMatch("/**")
                .build();
    }

}






//package com.example.GestionClinique.configuration.swaggerConfig;
//
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.ExternalDocumentation;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//public class SwaggerConfiguration { // Renamed from SwaggerConfig for clarity
//
//    @Bean
//    @Primary //
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new io.swagger.v3.oas.models.info.Info()
//                        .title("API avec JWT")
//                        .version("1.0"))
//                 .components(new Components()
//                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
//                                .name("Authorization")
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")))
//                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
//    }
//
//    @Bean
//    public OpenAPI gestionCliniqueOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("Gestion Clinique Rest API") // Your title
//                        .description("Documentation Api de Gestion d'une clinique") // Your description
//                        .version("v0.0.1") // Your application version
//                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("Gestion Clinique Wiki Documentation")
//                        .url("https://springdoc.org/")); // Or your actual wiki URL
//    }
//
//
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("Gestion Clinique API") // The name of your group
//                .pathsToMatch("/**") // Document all paths. Adjust if you have a specific base path, e.g., "/api/**"
//                .packagesToScan("com.example.GestionClinique") // Scan your main package
//                .build();
//    }
//
//}




//package com.tchindaClovis.gestiondestock.config;
//
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import static com.tchindaClovis.gestiondestock.utils.Constants.APP_ROOT;
//
//@Configuration
//@EnableSwagger2 // activer Swagger ou pour dire à spring d'exécuter cette classe au démarrage de l'application
//public class SwaggerConfiguration {
//
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(
//                        new ApiInfoBuilder()
//                                .description("Gestion de stock API documentation")
//                                .title("Gestion de stock REST API")
//                                .build()
//                )
//                .groupName("REST API V1")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.tchindaClovis.gestiondestock"))
//                .paths(PathSelectors.ant(APP_ROOT + "/**"))
//                .build();
//    }
//}
