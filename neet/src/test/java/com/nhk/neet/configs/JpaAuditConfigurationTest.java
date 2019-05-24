package com.nhk.neet.configs;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class JpaAuditConfigurationTest {

  @Test
  public void getAuditingContext() {
    var config = new JpaAuditConfiguration();
    Optional<String> auditor = config.getAuditingContext().getCurrentAuditor();

    assertThat(auditor.get())
        .isEqualTo("Guest");
  }
}