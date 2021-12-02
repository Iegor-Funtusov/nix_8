package ua.com.alevel;

import java.io.*;
import java.lang.instrument.Instrumentation;

public class SerialTest {

    public void test() {
//        Student student = new Student();
//        student.setAge(20);
//        student.setFirstName("test1");
//        student.setLastName("test2");
//        student.setFullName("test1 test2");
//
//        System.out.println("student = " + student);

//        try(ObjectOutputStream dataOutputStream = new ObjectOutputStream(new FileOutputStream("student.out"))) {
//            dataOutputStream.writeObject(student);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("student.out"))) {
//            Student std = (Student) objectInputStream.readObject();
//            System.out.println("std = " + std);
//            System.out.println("std = " + std.getFullName());
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        Singleton.getInstance().print();
        int i = Singleton.getInstance().hashCode();
        System.out.println("i = " + i);

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("singleton.out"))) {
            outputStream.writeObject(Singleton.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("singleton.out"))) {
            Singleton singleton = (Singleton) inputStream.readObject();
            singleton.print();
            System.out.println("singleton = " + singleton.hashCode());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(Singleton.getInstance().getClassSingl().getName());
            System.out.println("aClass = " + aClass.hashCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
