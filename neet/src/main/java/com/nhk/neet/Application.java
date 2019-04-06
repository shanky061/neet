package com.nhk.neet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

@ComponentScan
@EnableAutoConfiguration
@EnableJpaAuditing
@SpringBootConfiguration
public class Application {
    public static void main(String... args) {
        SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
    }

    @Bean
    public AuditorAware<String> getAuditingContext() {
        return () -> Optional.of("Guest");
    }
}
