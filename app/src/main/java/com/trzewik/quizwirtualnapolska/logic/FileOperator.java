package com.trzewik.quizwirtualnapolska.logic;


import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperator {
    private DirectoryCreator directoryCreator = new DirectoryCreator();
    private ImageLoader imageLoader = new ImageLoader();

    public String writeImageToFileAndGetPath(String appDirectory, String filesDirectory, String imageAddress) {
        try {
            writeImageToFile(appDirectory, filesDirectory, imageAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appDirectory + filesDirectory + imageLoader.getImageName(imageAddress);
    }

    private void writeImageToFile(String appDirectory, String filesDirectory, String imageAddress) throws IOException {
        byte[] byteArray = imageLoader.getImageAsByteArray(imageLoader.getResizedImageAsBitmap(imageAddress));
        directoryCreator.createDirectory(appDirectory, filesDirectory);
        FileOutputStream fileOutputStream = new FileOutputStream(appDirectory + filesDirectory + imageLoader.getImageName(imageAddress));
        try {
            fileOutputStream.write(byteArray);
        } finally {
            fileOutputStream.close();
        }
    }
}
