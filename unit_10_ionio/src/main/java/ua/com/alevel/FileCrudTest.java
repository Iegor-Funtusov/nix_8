package ua.com.alevel;

public interface FileCrudTest {

    void createFile(String pathToFile);
    void readInfo(String pathToFile);
    void createFolder(String pathToFolder);
    void createFolders(String pathToFolder);
    void readInfoIntoFolder(String pathToFolder);
    void removeFolders(String pathToFolder);
    void renameFile(String pathFrom, String pathTo);
}
