package ua.com.alevel;

import ua.com.alevel.recordclass.ImmutableClass;
import ua.com.alevel.recordclass.RecordClass;

public class AboutObjectMain {

    public static void main(String[] args) {
        System.out.println("AboutObjectMain.main");

        final ImmutableClass immutableClass = new ImmutableClass("1", "1", "1");
        final RecordClass recordClass = new RecordClass("1", "1", "1");

        System.out.println("immutableClass = " + immutableClass.getSome1());
        System.out.println("recordClass = " + recordClass.some1());


        ImmutableClass immutableClass1 = new ImmutableClass("2", "2", "2");

//        immutableClass = immutableClass1;
//        System.out.println("immutableClass = " + immutableClass.getSome1());

        User user1 = new User();
        user1.setName("name1");
        ContainsUser containsUser = new ContainsUser(user1);
        System.out.println("containsUser = " + containsUser.getUser().getName());

        User user2 = containsUser.getUser();
        user2.setName("name2");

        System.out.println("containsUser = " + containsUser.getUser().getName());
    }
}
