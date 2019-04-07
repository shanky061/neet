package com.nhk.neet.helper;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;

public class HashingHelperTest {
  private final HashingHelper hashingHelper = new HashingHelper();

  @Test
  public void test_getNextHash() {
    // check if values returned are all unique
    final int iterationCount = 15;
    final Set<String> uniqueHashes = new HashSet<>(iterationCount, 1F);

    for (int i = 0; i < iterationCount; ++i) {
      String hash = hashingHelper.getNextHash();
      uniqueHashes.add(hash);
    }

    Assert.assertThat(uniqueHashes.size(), is(iterationCount));
  }
}