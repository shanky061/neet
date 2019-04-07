package com.nhk.neet.repository;

import com.nhk.neet.model.TinyUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinyUrlRepository extends JpaRepository<TinyUrl, Long> {
  TinyUrl findByHash(String hash);
}
