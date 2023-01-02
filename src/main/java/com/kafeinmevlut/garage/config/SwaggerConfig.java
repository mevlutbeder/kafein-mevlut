package com.kafeinmevlut.garage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

/**
 * @author mevlutbeder
 * @created 02/01/2023 02:57
 */
/*@Configuration
@EnableSwagger2*/
public class SwaggerConfig {

/*  @Bean
  public Docket userApi() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.kafeinmevlut.garage")).paths(paths()).build();
  }

  private
  Predicate<String> paths() {
    return PathSelectors.ant("/status");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("User REST API").description("User API Documentation")
            .termsOfServiceUrl("http://bislim.io").license("Bilisimio License").licenseUrl("cem@bilisim.io")
            .version("1.0").build();
  }*/
}