package ua.com.alevel.stream;

import java.util.ArrayList;
import java.util.List;

public class ParallelTest {

    public List<Integer> integers = new ArrayList<>();

    public void init() {
        for (int i = 0; i < 100_000; i++) {
            integers.add(i);
        }
    }

    public void test() {
        int sum = 0;
        int sum1 = 0;
        int sum2 = 0;

        long start = System.currentTimeMillis();
        sum = integers.stream().reduce(Integer::sum).orElse(0);
        long end = System.currentTimeMillis() - start;
        System.out.println("simple stream end = " + end);
        System.out.println("sum = " + sum);

        start = System.currentTimeMillis();
        sum1 = integers.stream().parallel().reduce(Integer::sum).orElse(0);
        end = System.currentTimeMillis() - start;
        System.out.println("stream parallel end = " + end);
        System.out.println("sum1 = " + sum1);

        start = System.currentTimeMillis();
        sum2 = integers.parallelStream().reduce(Integer::sum).orElse(0);
        end = System.currentTimeMillis() - start;
        System.out.println("parallel stream end = " + end);
        System.out.println("sum2 = " + sum2);
    }
}
