package com.pinket.springbootrest.greeting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void greeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("Hello, World!")))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.id", either(isA(Long.class)).or(isA(Integer.class))));
    }

    @Test
    public void greetingWithName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting?name=Test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("Hello, Test!")))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.id", either(isA(Long.class)).or(isA(Integer.class))));
    }
}
