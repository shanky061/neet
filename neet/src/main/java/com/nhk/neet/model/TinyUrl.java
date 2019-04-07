package com.nhk.neet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TinyUrl extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(length = 7, unique = true)
  private String hash;

  /**
   * A url resource with url length limitations
   */
  @NotNull
  @Column(length = 2047)
  private String resource;

  public TinyUrl() {
  }

  public TinyUrl(String hash, String resource) {
    this.hash = hash;
    this.resource = resource;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }
}
