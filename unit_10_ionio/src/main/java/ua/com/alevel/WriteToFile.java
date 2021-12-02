package ua.com.alevel;

import java.io.*;

public class WriteToFile {

    public void test(String pathToFile) {
//        Reader reader;
//        Writer writer;
//
//        InputStream inputStream;
//        OutputStream outputStream;
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream(pathToFile);
////            int read = fileInputStream.read();
////            System.out.println("read = " + read);
//            byte[] bytes = fileInputStream.readAllBytes();
//            for (byte aByte : bytes) {
//                System.out.println("byte = " + aByte);
//                System.out.println("char = " + (char) aByte);
//            }
//            fileInputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

//        System.out.println();
//        try(FileInputStream fileInputStream = new FileInputStream(pathToFile);
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
//            while (bufferedReader.ready()) {
//                String s = bufferedReader.readLine();
//                System.out.println("s = " + s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("test_new.txt");
//            fileOutputStream.write(104);
//            fileOutputStream.write(101);
//            fileOutputStream.write(108);
//            fileOutputStream.write(108);
//            fileOutputStream.write(111);
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            FileReader fileReader = new FileReader("test_new.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            while (bufferedReader.ready()) {
//                System.out.println("fileReader = " + bufferedReader.readLine());
//            }
//            bufferedReader.close();
//            fileReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            FileWriter fileWriter = new FileWriter("test_new.txt", true);
            fileWriter.write("\n");
            fileWriter.write("test1");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
