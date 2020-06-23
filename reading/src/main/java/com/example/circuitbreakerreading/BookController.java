package com.example.circuitbreakerreading;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {

    @GetMapping("/latest")
    @HystrixCommand(fallbackMethod = "fallbackHello", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<String> latestBooks() throws InterruptedException {
        Thread.sleep(3000);
        return Arrays.asList("Dark 4", "Stranger Things 4");
    }

    public List<String> fallbackHello() {
        return Arrays.asList("No hay nuevos libros.");
    }

}
