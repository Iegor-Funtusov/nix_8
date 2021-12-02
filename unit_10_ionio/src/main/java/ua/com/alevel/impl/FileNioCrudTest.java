package ua.com.alevel.impl;

import ua.com.alevel.FileCrudTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileNioCrudTest implements FileCrudTest {

    @Override
    public void createFile(String pathToFile) {
        Path path = Paths.get(pathToFile);
        try {
            if (!Files.exists(path)){
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readInfo(String pathToFile) {
        Path path = Paths.get(pathToFile);
        boolean regularFile = Files.isRegularFile(path);
        System.out.println("regularFile = " + regularFile);
    }

    @Override
    public void createFolder(String pathToFolder) {
        Path path = Paths.get(pathToFolder);
        try {
            if (!Files.exists(path)){
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createFolders(String pathToFolder) {
        Path path = Paths.get(pathToFolder);
        try {
            if (!Files.exists(path)){
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readInfoIntoFolder(String pathToFolder) {

    }

    @Override
    public void removeFolders(String pathToFolder) {
        System.out.println("pathToFolder = " + pathToFolder);
        Path path = Paths.get(pathToFolder);
        try {
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renameFile(String pathFrom, String pathTo) {
        Path from = Paths.get(pathFrom);
        Path to = Paths.get(pathTo);
        try {
            Files.move(from, to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
