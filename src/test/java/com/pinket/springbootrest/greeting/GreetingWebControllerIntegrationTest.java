package com.pinket.springbootrest.greeting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingWebControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private URL baseUrl;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.baseUrl = new URL("http://localhost:" + port + "/web/greeting");
    }

    @Test
    public void greeting() throws Exception {
        ResponseEntity<String> response = template.getForEntity(baseUrl.toString(), String.class);
        assertThat(response.getBody()).contains("Hello, World!");
    }

    @Test
    public void greetingWithName() throws Exception {
        ResponseEntity<String> response = template.getForEntity(baseUrl.toString() + "?name=Test", String.class);
        assertThat(response.getBody()).contains("Hello, Test!");
    }
}
