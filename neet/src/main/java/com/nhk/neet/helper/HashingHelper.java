package com.nhk.neet.helper;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class HashingHelper {

  //    private final Random randomGenerator = new Random(new Date().getTime());
  private long lastIndex = 0;
  private final char[] base62Chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

  private Supplier<String> randomSequenceGenerator = () -> {
    long nextIndex = ++lastIndex;
    StringBuilder builder = new StringBuilder();
    while (nextIndex > 0) {
      int offset = (int) (nextIndex % 62);
      builder.append(base62Chars[offset]);
      nextIndex = nextIndex / 62;
    }
    return builder.toString();
  };

  /**
   * Generate pseudo-unique hash
   *
   * @return pseudo-unique character sequence
   */
  public String getNextHash() {
    return randomSequenceGenerator.get();
  }
}
