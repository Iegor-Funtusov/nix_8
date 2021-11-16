package ua.com.alevel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    private static final int SIZE = 100_000;
    private List<Integer> arrayList = new ArrayList<>(SIZE);
    private List<Integer> linkedList = new LinkedList<>();

    public void test() {
        add();
//        get();
        delete();
    }

    private void add() {
        arrayList.clear();
        linkedList.clear();
        System.out.println("ListTest.add");
        System.out.println("arrayList start");
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arrayList.add(i, 10);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList finish: " + end + ".ms");

        System.out.println("linkedList start");
        start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            linkedList.add(i);
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList finish: " + end + ".ms");
    }

    private void delete() {
        System.out.println("ListTest.delete");
        System.out.println("arrayList start");
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList finish: " + end + ".ms");

        System.out.println("linkedList start");
        start = System.currentTimeMillis();
        iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList finish: " + end + ".ms");
    }

    private void get() {
        System.out.println("ListTest.get");
        System.out.println("arrayList start");
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE - 1; i++) {
            arrayList.get(i);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList finish: " + end + ".ms");

        System.out.println("linkedList start");
        start = System.currentTimeMillis();
        for (int i = 0; i < SIZE - 1; i++) {
            linkedList.get(i);
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList finish: " + end + ".ms");
    }
}
