package com.codenation.central.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static String ACCESS_TOKEN_URI = "/api/v1/oauth/token";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.codenation.central.api.resource"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Arrays.asList(securitySchema()))
                .apiInfo(apiInfo());
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return SecurityConfigurationBuilder.builder()
                .clientId("codenation")
                .clientSecret("codenation")
                .realm("")
                .appName("Teste")
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = new ArrayList();
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("write", "access all"));

        List<GrantType> grantTypes = new ArrayList();
        GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(ACCESS_TOKEN_URI);
        grantTypes.add(passwordCredentialsGrant);

        return new OAuth("oauth2", authorizationScopeList, grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");
        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API REST - Central de erros")
                .description("Projeto prático e final do AceleraDev Java Online")
                .license("Open Source")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .build();

    }
}