package com.nhk.neet.repository;

import com.nhk.neet.model.TinyUrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
public class TinyUrlRepositoryTest {

  @Autowired
  private TinyUrlRepository tinyUrlRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void test_findByHash() {
    Date dateNow = new Date();
    TinyUrl entityToSave = new TinyUrl("xyz", "s.co");
    entityToSave.setCreatedAt(dateNow);
    entityToSave.setUpdatedAt(dateNow);
    entityToSave.setCreatedBy("Guest");

    TinyUrl savedUrl = testEntityManager.persist(entityToSave);
    testEntityManager.flush();

    TinyUrl tinyUrl = tinyUrlRepository.findByHash("xyz");

    assertNotNull(tinyUrl.getId());
    assertThat(tinyUrl.getHash(), is(savedUrl.getHash()));
    assertThat(tinyUrl.getResource(), is(savedUrl.getResource()));
  }
}