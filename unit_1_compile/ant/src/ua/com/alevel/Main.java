package ua.com.alevel;

import ua.com.alevel.test.Test;

class Main {

    public final static int i;

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.getClass().getName());
        System.out.println(main.getClass().getSimpleName());
//        ua.com.alevel.test.Test test = new ua.com.alevel.test.Test();
//        var test = new ua.com.alevel.test.Test();
        Test test = new Test();
        test.run();
    }
}
