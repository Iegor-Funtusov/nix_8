package ua.com.alevel;

import ua.com.alevel.impl.FileIOCrudTest;
import ua.com.alevel.impl.FileNioCrudTest;
import ua.com.alevel.json.JsonTest;

public class IoNioMain {

    private static final String PATH_TO_FILE = "file.txt";
    private static final String PATH_TO_MV_FILE = "file.txt";
    private static final String PATH_TO_FOLDER = "test";
    private static final String PATH_TO_FOLDERS = "test/test1/test2";
    private static final String PATH_TO_END_FOLDERS_FILE = "test/test1/test2/test2.txt";

    public static void main(String[] args) {
        System.out.println("IoNioMain.main");
        FileCrudTest io = new FileIOCrudTest();
//        io.createFile(PATH_TO_FILE);
        FileCrudTest nio = new FileNioCrudTest();
//        nio.createFile(PATH_TO_FILE);
//
//        io.readInfo(PATH_TO_FILE);
//        nio.readInfo(PATH_TO_FILE);

//        io.createFolder(PATH_TO_FOLDER);
//        nio.createFolder(PATH_TO_FOLDER);

//        io.createFolders(PATH_TO_FOLDERS);
//        nio.createFolders(PATH_TO_FOLDERS);

//        io.readInfoIntoFolder(PATH_TO_FOLDER);
//        io.removeFolders(PATH_TO_FOLDER);
//        nio.removeFolders(PATH_TO_END_FOLDERS_FILE);

//        nio.renameFile(PATH_TO_FILE, PATH_TO_MV_FILE);
//        WriteToFile writeToFile = new WriteToFile();
//        writeToFile.test(PATH_TO_FILE);

//        SerialTest serialTest = new SerialTest();
//        serialTest.test();

//        new JsonTest().test();
        new CsvTest().test();
    }
}
