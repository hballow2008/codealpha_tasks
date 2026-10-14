package com.habeeb.devops;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "app", "Habeeb DevOps App",
                "status", "running",
                "version", "1.0.0");
    }

    @GetMapping("/api/greet")
    public Map<String, String> greet(@RequestParam(required = false) String name) {
        return Map.of("message", greetingService.greet(name));
    }
}