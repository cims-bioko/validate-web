package com.github.cimsbioko.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.web.reactive.function.BodyInserters.fromResource;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class ApplicationTests {

    @Autowired
    private WebTestClient client;

    @Test
    public void simplest() {
        client.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_XML)
                .body(fromResource(loadForm("simplest")))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(s -> assertTrue(s.contains("\"valid\":true") && s.contains("\"message\":\"xform is valid\"")));
    }

    @Test
    public void simplestBroken() {
        client.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_XML)
                .body(fromResource(loadForm("simplest-broken")))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .value(s -> assertTrue(s.contains("\"valid\":false") && s.contains("\"message\":\"xform is invalid")));
    }


    private Resource loadForm(String name) {
        return new ClassPathResource(String.format("/%s.xml", name));
    }
}
