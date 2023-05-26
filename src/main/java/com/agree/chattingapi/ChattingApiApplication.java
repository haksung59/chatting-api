package com.agree.chattingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class ChattingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChattingApiApplication.class, args);
    }

}
