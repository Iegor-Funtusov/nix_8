package ua.com.alevel.impl;

import org.apache.commons.io.FileUtils;
import ua.com.alevel.FileCrudTest;

import java.io.File;
import java.io.IOException;

public class FileIOCrudTest implements FileCrudTest {

    @Override
    public void createFile(String pathToFile) {
        File file = new File(pathToFile);
        System.out.println("file = " + file.getAbsolutePath());
        System.out.println("file = " + file.exists());
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file = " + file.exists());
    }

    @Override
    public void readInfo(String pathToFile) {
        File file = new File(pathToFile);
        System.out.println("is file = " + file.isFile());
    }

    @Override
    public void createFolder(String pathToFolder) {
        File file = new File(pathToFolder);
        file.mkdir();
    }

    @Override
    public void createFolders(String pathToFolder) {
        File file = new File(pathToFolder);
        file.mkdirs();
    }

    @Override
    public void readInfoIntoFolder(String pathToFolder) {
        readDirs(new File(pathToFolder));
    }

    @Override
    public void removeFolders(String pathToFolder) {
        File file = new File(pathToFolder);
        file.delete();

        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renameFile(String pathFrom, String pathTo) {

    }

    private void readDirs(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (File child : children) {
                if (child.isDirectory()) {
                    System.out.println("dir = " + child.getAbsolutePath());
                } else {
                    System.out.println("\t file = " + child.getAbsolutePath());
                }
                readDirs(child);
            }
        }
    }
}
