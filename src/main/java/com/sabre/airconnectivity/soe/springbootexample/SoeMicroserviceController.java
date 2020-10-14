package com.sabre.airconnectivity.soe.springbootexample;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class SoeMicroserviceController {

    private static final int GREETING_DELAY = 10000;
    private static final Logger LOGGER = LogManager.getLogger(SoeMicroserviceController.class);

    private final AtomicInteger soeGreetingCounter;
    private String name;

    public SoeMicroserviceController() {
        this.soeGreetingCounter = new AtomicInteger(0);
        this.name = "Hello World!";
    }

    @GetMapping("/")
    public List<String> mainPage() {
        return Arrays.asList("http://localhost:8080/greeting", "http://localhost:8081/actuator");
    }

    @GetMapping("/greeting")
    public GreetingModel greeting() throws InterruptedException {
        LOGGER.info("Got greeting request");
        Thread.sleep(GREETING_DELAY);
        GreetingModel greeting = new GreetingModel(soeGreetingCounter.getAndIncrement(), name);
        LOGGER.info("return greeting");
        return greeting;
    }
}
