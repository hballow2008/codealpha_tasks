package com.habeeb.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @Test
    void greetsByName() {
        assertEquals("Hello, Habeeb!", service.greet("Habeeb"));
    }

    @Test
    void trimsExtraSpaces() {
        assertEquals("Hello, Habeeb!", service.greet("  Habeeb  "));
    }

    @Test
    void defaultsToWorldWhenNameMissing() {
        assertEquals("Hello, World!", service.greet(null));
        assertEquals("Hello, World!", service.greet("   "));
    }
}