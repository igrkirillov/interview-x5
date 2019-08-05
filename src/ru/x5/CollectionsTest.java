package ru.x5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionsTest {

  private static final String X5 = "X5";

  public static void main(String[] args) {
    // data-set
    Random random = new Random();
    List<String> data = IntStream.generate(() -> random.nextInt()).boxed().limit((int) Math.pow(10,6))
        .map(i -> String.valueOf(i))
        .collect(Collectors.toList());
    int middle = data.size() >> 1;

    ArrayList<String> arrayList = new ArrayList<>(data);
    LinkedList<String> linkedList = new LinkedList<>(data);

    // добавляем элемент в середину arrayList
    long start1 = System.currentTimeMillis();
    arrayList.add(middle, X5);
    long test1 = System.currentTimeMillis() - start1;

    // добавляем элемент в середину linkedList
    long start2 = System.currentTimeMillis();
    linkedList.add(middle, X5);
    long test2 = System.currentTimeMillis() - start2;

    // смотрим что получилось
    System.out.println(String.format("ArrayList(%s ms) %s LinkedList(%s ms)",
        test1,
        test1 == test2 ? "=" : (test1 > test2) ? ">" : "<",
        test2));
  }
}
