/**
 * Swagger configuration class
 */
package com.turvo.banking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author anushm
 *
 */
@EnableSwagger2
@Profile("!test")
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.
                		withClassAnnotation(RestController.class))
                .build().apiInfo(metaData());

    }
    
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Customer Queue System",
                "REST API for Maintaining Customer Queues in Bank",
                "1.0",
                "Terms of service",
                new Contact("Anush Mannam", "https://github.com/anushm20/turvo-banking", "makanush@gmail.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }

}
