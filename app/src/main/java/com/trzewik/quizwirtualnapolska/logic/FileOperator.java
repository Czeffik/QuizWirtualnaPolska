package com.trzewik.quizwirtualnapolska.logic;


import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperator {
    private DirectoryCreator directoryCreator = new DirectoryCreator();

    public String writeImageToFile(String appDirectory, String filesDirectory, String imageUniqueId, byte[] byteArray) throws IOException {
        String pathToImage = appDirectory + filesDirectory + imageUniqueId;
        directoryCreator.createDirectory(appDirectory, filesDirectory);
        FileOutputStream fileOutputStream = new FileOutputStream(pathToImage);
        try {
            fileOutputStream.write(byteArray);
        } finally {
            fileOutputStream.close();
        }
        return pathToImage;
    }
}
