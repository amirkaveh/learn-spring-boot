package com.pinket.springbootrest.greeting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private URL baseUrl;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUP() throws Exception {
        this.baseUrl = new URL("http://localhost:" + port + "/greeting");
    }

    @Test
    public void greeting() {
        ResponseEntity<String> response = template.getForEntity(baseUrl.toString(), String.class);
        JsonContentAssert jsonContentAssert = new JsonContentAssert(response.getClass(), response.getBody());
        jsonContentAssert.extractingJsonPathValue("$.content")
                .isEqualTo("Hello, World!");
        jsonContentAssert.extractingJsonPathValue("$.id")
                .isNotNull()
                .isInstanceOfAny(Long.class, Integer.class);
    }

    @Test
    public void greetingWithName() {
        ResponseEntity<String> response = template.getForEntity(baseUrl.toString() + "?name=Test", String.class);
        JsonContentAssert jsonContentAssert = new JsonContentAssert(response.getClass(), response.getBody());
        jsonContentAssert.extractingJsonPathValue("$.content")
                .isEqualTo("Hello, Test!");
        jsonContentAssert.extractingJsonPathValue("$.id")
                .isNotNull()
                .isInstanceOfAny(Long.class, Integer.class);

    }
}
