package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseOperationsMain {

    public static void main(String[] args) {
        System.out.println("BaseOperationsMain.main");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            System.out.println("s = " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
