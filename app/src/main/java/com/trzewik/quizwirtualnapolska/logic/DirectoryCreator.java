package com.trzewik.quizwirtualnapolska.logic;


import java.io.File;
import java.util.logging.Logger;

class DirectoryCreator {
    final static private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    void createDirectory(String pathToDirectory, String newDirectoryName) {
        File folder = new File(pathToDirectory + newDirectoryName);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                LOGGER.info("Folder created!");
            } else {
                LOGGER.info("Folder not created! Retrying...");
                createDirectory(pathToDirectory,newDirectoryName);
            }
        }
    }
}
