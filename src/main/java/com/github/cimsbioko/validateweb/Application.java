package com.github.cimsbioko.validateweb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.resources;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ValidationService validationService() {
        return new DefaultValidationService();
    }

    @Bean
    RouterFunction staticFiles() {
        return resources("/**", new ClassPathResource("META-INF/resources/"));
    }

    @Bean
    RouterFunction indexMapping(@Value("classpath:/META-INF/resources/index.html") final Resource indexHtml) {
        return route(GET("/"),
                request -> ok().contentType(MediaType.TEXT_HTML).syncBody(indexHtml));
    }
}