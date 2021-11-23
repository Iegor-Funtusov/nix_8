package ua.com.alevel.stream;

import java.util.*;
import java.util.stream.Collectors;

public class PointerTest {

    private List<Integer> integers = Arrays.asList(9, 56, 8, 90);
    private List<Profile> profiles = Arrays.asList(
            new Profile("test1", "test11"),
            new Profile("test2", "test22"),
            new Profile("test3", "test33"),
            new Profile("test4", "test44"),
            new Profile("test5", "test55")
    );

    private Map<String, String> map = Map.of(
            "test1", "test11",
            "test2", "test22",
            "test3", "test33",
            "test4", "test44",
            "test5", "test55",
            "test6", "test66",
            "test7", "test77"
    );

    private List<String> strings = Arrays.asList(
            "test@test.com",
            "test@testcom",
            "test@test.com",
            "test1@test.",
            "test1@test.................................",
            "@test.com",
            "test222@test.com",
            "test1test.com",
            "test1@test.com"
    );

    private List<Integer> chars = Arrays.asList(65, 77, 98, 122, 90);

    public void test() {
        //1
//        boolean containsEvenNumber = false;
//        boolean containsEvenNumber = integers.stream().anyMatch(integer -> isEven(integer));
        boolean containsEvenNumber = integers.stream().anyMatch(this::isEven);
        System.out.println("containsEvenNumber = " + containsEvenNumber);
        containsEvenNumber = integers.stream().anyMatch(NumberUtil::isEven);
        System.out.println("containsEvenNumber = " + containsEvenNumber);
//        for (Integer integer : integers) {
//            if (isEven(integer)) {
//                containsEvenNumber = true;
//            }
//        }
        List<String> fullNames = profiles.stream()
                .map(profile -> profile.firstName + " " + profile.lastName)
                .collect(Collectors.toList());
        System.out.println("fullNames = " + fullNames);

        fullNames = profiles.stream()
                .map(Profile::getFullName)
                .collect(Collectors.toList());
        System.out.println("fullNames = " + fullNames);

        List<Profile> profiles = map
                .entrySet()
                .stream()
                .map(k -> new Profile(k.getKey(), k.getValue()))
                .collect(Collectors.toList());
        System.out.println("profiles = " + profiles);

        profiles = map
                .entrySet()
                .stream()
                .map(Profile::new)
                .collect(Collectors.toList());
        System.out.println("profiles = " + profiles);

        String finalEmail = strings
                .stream()
                .distinct()
                .filter(s -> !s.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"))
//                .max((o1, o2) -> Integer.compare(o1.length(), o2.length()))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("finalEmail = " + finalEmail);

        String result = chars.stream()
                .map(Character::toChars)
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("result = " + result);
    }

    public boolean isEven(int a) {
        return a % 2 == 0;
    }

    private static class Profile {

        private String firstName;
        private String lastName;

        public Profile(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Profile(Map.Entry<String, String> entry) {
            this.firstName = entry.getKey();
            this.lastName = entry.getValue();
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        @Override
        public String toString() {
            return "Profile{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

//    private static class Tuple<K, V> {
//
//        private K k;
//        private V v;
//
//        public Object getVal() {
//            return k, v;
//        }
//    }
}
