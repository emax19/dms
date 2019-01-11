package com.emax.dms.persistence.config;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;


@Configuration
@EnableSwagger2WebMvc
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class SwaggerConfig extends SpringBootServletInitializer {

    @Bean
    Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build()
                .directModelSubstitute(Enum.class, String.class)
                .apiInfo(new ApiInfoBuilder()
                        .title("dms")
                        .description("dms documentation, last deploy: " + ZonedDateTime.now(ZoneOffset.of("+3")))
                        .build());
    }

}