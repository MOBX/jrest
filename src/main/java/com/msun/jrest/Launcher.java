/*
 * Copyright 2015-2020 uuzu.com All right reserved.
 */
package com.msun.jrest;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
/**
 * @author zxc Apr 1, 2017 3:39:07 PM
 */
@Configuration
@SpringBootApplication
@EnableSwagger2
public class Launcher extends SpringBootServletInitializer {

    @Autowired
    private TypeResolver typeResolver;

    private ApiKey apiKey() {
        return new ApiKey("mykey", "api_key", "header");
    }

    public static void main(String[] args) {
        new SpringApplication(Launcher.class).run(args);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {

                ErrorPage page401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
                ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
                ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
                container.addErrorPages(page401, page404, page500);
            }
        };
    }

    @SuppressWarnings("deprecation")
    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)//
        .groupName("business-api")//
        .protocols(Sets.newHashSet("http"))//
        .forCodeGeneration(true)//
        .select()//
          .apis(RequestHandlerSelectors.any())//
          .paths(PathSelectors.any())//
          .build()//

        .apiInfo(new ApiInfoBuilder()//
          .contact("zhangxiongcai337@gmail.com")//
          .title("jrest")//
          .description("Using HTTP Methods for RESTful Services .")//
          .termsOfServiceUrl("http://www.msun.com")//
          .license("Apache License Version 2.0")//
          .licenseUrl("https://github.com/MOBX/jrest/blob/master/LICENSE")//
          .version("1.0").build())//

        .pathMapping("/api")//
        .directModelSubstitute(LocalDate.class,String.class)//
        .genericModelSubstitutes(ResponseEntity.class)//
        .alternateTypeRules(//
            newRule(typeResolver.resolve(DeferredResult.class,//
                    typeResolver.resolve(ResponseEntity.class, WildcardType.class)),//
                    typeResolver.resolve(WildcardType.class)))//
        .useDefaultResponseMessages(false)//
        .globalResponseMessage(RequestMethod.GET,//
            newArrayList(new ResponseMessageBuilder()//
                .code(500)//
                .message("500 message")//
                .responseModel(new ModelRef("Error"))//
                .build()))//
        .securitySchemes(newArrayList(apiKey()))//
        .securityContexts(newArrayList(securityContext()))//
        .enableUrlTemplating(true)//
        .globalOperationParameters(//
            newArrayList(new ParameterBuilder()//
                .name("someGlobalParameter")//
                .description("Description of someGlobalParameter")//
                .modelRef(new ModelRef("string"))//
                .parameterType("query")//
                .required(true)//
                .build()))//
        .tags(new Tag("Pet Service", "All apis relating to pets")) //
        ;
        // .additionalModels(typeResolver.resolve(AdditionalModel.class)) ;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/anyPath.*")).build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference("mykey", authorizationScopes));
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration("test-app-client-id", 
                                         "test-app-client-secret", 
                                         "test-app-realm", 
                                         "test-app", 
                                         "apiKey", 
                                         ApiKeyVehicle.HEADER, 
                                         "api_key", ",");
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration("validatorUrl",// url
                                   "none", // docExpansion => none | list
                                   "alpha", // apiSorter => alpha
                                   "schema", // defaultModelRendering => schema
                                   UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, // enableJsonEditor => true
                                                                                            // | false
                                   true, // showRequestHeaders => true | false
                                   60000L); // requestTimeout => in milliseconds, defaults to null (uses jquery xh
                                            // timeout)
    }
}
