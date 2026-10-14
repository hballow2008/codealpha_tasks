package com.habeeb.devops;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String greet(String name) {
        if (name == null || name.isBlank()) {
            return "Hi, World!";
        }
        return "Hello, " + name.trim() + "!";
    }
}