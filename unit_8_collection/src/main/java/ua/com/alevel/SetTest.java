package ua.com.alevel;

import java.util.*;

public class SetTest {

    public void test() {
        System.out.println("SetTest.test");
        Set<User> users = new HashSet<>();
        Set<User> newUsers = new TreeSet<>();
        Map<User, String> userStringMap = new TreeMap<>();
        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setAge(10);
            user.setName("name"+i);
            users.add(user);
            newUsers.add(user);
            userStringMap.put(user, UUID.randomUUID().toString());
        }
        for (User user : users) {
            System.out.println("user = " + user);
        }
        System.out.println();
        for (User user : newUsers) {
            System.out.println("user = " + user);
        }
        userStringMap.forEach((k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
        });
    }
}
