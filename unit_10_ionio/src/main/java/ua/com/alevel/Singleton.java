package ua.com.alevel;

import java.io.Serializable;

public class Singleton implements Serializable {

    private static Singleton instance;

    private Singleton() {
        System.out.println("Singleton.Singleton");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void print() {
        System.out.println("Singleton.print");
    }

    public Class<?> getClassSingl() {
        return this.getClass();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
