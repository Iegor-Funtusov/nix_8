package ua.com.alevel;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class StringsMain {

    private static final String NAME_1 = "name1";
    private static final String NAME_2 = "name2";

    public static void main(String[] args) {

        String s = "\n   \t";

        System.out.println("s = " + !s.isBlank());
        System.out.println("s = " + !s.isEmpty());

        System.out.println("s = " + StringUtils.isNotBlank(s));
        System.out.println("s = " + StringUtils.isNotEmpty(s));

        s = "yuktriyu";

        System.out.println("s = " + ReverseStringUtil.reverse(s, true));


//        Random random = new Random();
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 1_000; i++) {
//            int ran = random.nextInt(2);
//            User user = new User();
//            if (ran == 0) {
//                user.setName(NAME_1);
//            } else {
//                user.setName(NAME_2);
//            }
//            user.setAge(i);
//            users.add(user);
//        }
//        long count = users.stream()
//                .filter(user -> user.getName().equals(NAME_1))
//                .count();
//        System.out.println("count = " + count);
    }
}
