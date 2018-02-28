package com.trzewik.quizwirtualnapolska.logic;


import java.io.File;

public class DirectoryCreator {
    public void createDirectory(String pathToDirectory, String newDirectoryName) {
        File folder = new File(pathToDirectory + newDirectoryName);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created!");
            } else {
                System.out.println("Error! Folder not created!");
            }
        }
    }
}
