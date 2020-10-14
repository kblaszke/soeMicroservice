package com.sabre.airconnectivity.soe.springbootexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class SoeMicroserviceController {

    private final AtomicInteger soeGreetingCounter;
    private String name;

    public SoeMicroserviceController() {
        this.soeGreetingCounter = new AtomicInteger(0);
        this.name = "Hello World!";
    }

    @GetMapping("/")
    public List<String> mainPage() {
        return Arrays.asList("http://localhost:8080/greeting");
    }

    @GetMapping("/greeting")
    public GreetingModel greeting() {
        return new GreetingModel(soeGreetingCounter.getAndIncrement(), name);
    }
}
