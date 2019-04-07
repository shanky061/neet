package com.nhk.neet.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfiguration {
  @Bean
  public AuditorAware<String> getAuditingContext() {
    return () -> Optional.of("Guest");
  }
}
