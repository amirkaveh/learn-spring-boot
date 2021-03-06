package com.pinket.springbootrest.greeting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingWebControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    private final String baseUrl = "/web/greeting";

    @Test
    public void greeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(baseUrl)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<p>Hello, World!</p>")));
    }

    @Test
    public void greetingWithName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(baseUrl + "?name=Test")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<p>Hello, Test!</p>")));
    }
}
