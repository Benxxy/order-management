package com.project.order.management.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "order-management.encoder")
@EnableConfigurationProperties
public class OrderManagementConfiguration {

    private int saltLength;
    private int keyLength;
    private int parallelism;
    private int memory;
    private int itterations;

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Argon2 Password hashing configuration: saltLength: {},keyLength: {}, parallelism: {}, memory: {}, iterations: {}", saltLength, keyLength, parallelism, memory, itterations);
        return new Argon2PasswordEncoder(
                saltLength,
                keyLength,
                parallelism,
                memory,
                itterations
        );
    }
}
