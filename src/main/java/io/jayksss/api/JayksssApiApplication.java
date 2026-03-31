package io.jayksss.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JayksssApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JayksssApiApplication.class, args);
    }

}
