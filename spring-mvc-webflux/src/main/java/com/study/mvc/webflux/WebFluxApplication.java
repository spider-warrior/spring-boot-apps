package com.study.mvc.webflux;

import com.study.mvc.webflux.client.GreetingWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebFluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
        GreetingWebClient client = new GreetingWebClient();
        System.out.println(client.getResult());
    }
}
