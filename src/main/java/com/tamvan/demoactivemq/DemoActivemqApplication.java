package com.tamvan.demoactivemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class DemoActivemqApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoActivemqApplication.class, args);
    }

}
