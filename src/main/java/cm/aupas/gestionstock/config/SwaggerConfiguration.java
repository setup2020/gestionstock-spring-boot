package cm.aupas.gestionstock.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
               // .securityContexts(Collections.singletonList(securityContext()))
                //.securitySchemes(Collections.singletonList(apiKey()))
               // .useDefaultResponseMessages(false)
                .select()

                .apis(RequestHandlerSelectors.basePackage("cm.aupas.gestionstock.controller"))
                .build()
               // .paths(PathSelectors.ant(APP_ROOT+"/**"))

                .apiInfo(apiInfo())
                ;
    }

    private ApiKey apiKey(){
        return  new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Gestion de stock",
                "Gestion de stock.",
                "1.0",
                "Terms of service",
                new Contact("pascal Gouchighe", "aupas.cm", "pascalngongang@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
    List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope
                =new AuthorizationScope(
                        "global","accessEnverything");

        AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
        return Collections.singletonList(
                new SecurityReference("JWT",authorizationScopes)
        );

    }
}
