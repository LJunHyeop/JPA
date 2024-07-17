package com.green.greengram;

import jakarta.persistence.Column;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@SpringBootApplication
@ConfigurationPropertiesScan //
@EnableJpaAuditing // auditing 기능 활성화
public class GreengramApplication {


    public static void main(String[] args) {
        SpringApplication.run(GreengramApplication.class, args);
    }

}
