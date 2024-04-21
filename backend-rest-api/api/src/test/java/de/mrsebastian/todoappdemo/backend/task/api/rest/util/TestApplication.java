package de.mrsebastian.todoappdemo.backend.task.api.rest.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("de.mrsebastian.todoappdemo.backend.task.api.rest")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }
}
