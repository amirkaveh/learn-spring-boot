package com.pinket.springbootrest;

import static org.assertj.core.api.Assertions.*;

import com.pinket.springbootrest.greeting.GreetingController;
import com.pinket.springbootrest.greeting.GreetingWebController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private GreetingController greetingController;

    @Autowired
    private GreetingWebController greetingWebController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(greetingController).isNotNull();
        assertThat(greetingWebController).isNotNull();
    }
}
