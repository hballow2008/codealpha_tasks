package com.habeeb.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HelloControllerTest {

    private final HelloController controller = new HelloController(new GreetingService());

    @Test
    void homeReportsRunning() {
        assertEquals("running", controller.home().get("status"));
    }

    @Test
    void greetEndpointUsesService() {
        assertEquals("Hello, Habeeb!", controller.greet("Habeeb").get("message"));
    }
}