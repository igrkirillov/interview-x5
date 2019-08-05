package ru.x5;

import java.util.stream.IntStream;

public class StreamTest {
  public static void main(String[] args) {
    IntStream.of(1,2,3,6,4,8)
        .peek(System.out::println)
        .filter(e -> e > 5)
        .forEach(System.out::println);
  }
}
