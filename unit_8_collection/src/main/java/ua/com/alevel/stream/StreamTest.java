package ua.com.alevel.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public void run() {
        List<String> strings = Arrays.asList("4", "da", "fda", "8", "1", "1", "daw", "3");
        List<Integer> integers = new ArrayList<>();
        List<Integer> notDublicatedintegers = new ArrayList<>();

        int sum = strings.stream()
                .skip(2)
                .distinct()
                .filter(s -> s.matches("\\d"))
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println("sum = " + sum);

        long count = strings.stream()
                .skip(2)
                .distinct()
                .filter(s -> s.matches("\\d"))
                .map(Integer::parseInt)
                .count();
        System.out.println("count = " + count);

        List<Integer> collect = strings.stream()
                .skip(2)
                .distinct()
                .filter(s -> s.matches("\\d"))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);


        //1
        // check parse string to int
//        for (String string : strings) {
//            try {
//                int i = Integer.parseInt(string);
//                integers.add(i);
//            } catch (NumberFormatException e) {
//                System.out.println("e = " + e.getMessage());
//            }
//        }
//
//        for (Integer integer : integers) {
//            if (!notDublicatedintegers.contains(integer)) {
//                notDublicatedintegers.add(integer);
//            }
//        }
//
//        sum = 0;
//        for (Integer notDublicatedinteger : notDublicatedintegers) {
//            sum += notDublicatedinteger;
//        }
//
//        System.out.println("sum = " + sum);


    }
}
