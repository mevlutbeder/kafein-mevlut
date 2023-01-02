package com.kafeinmevlut.garage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mevlutbeder
 * @created 02/01/2023 04:31
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.kafeinmevlut.garage.controller")
public class WebConfig implements WebMvcConfigurer {
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    return new MethodValidationPostProcessor();
  }
}
