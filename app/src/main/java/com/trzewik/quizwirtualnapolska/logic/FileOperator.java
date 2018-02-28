package com.trzewik.quizwirtualnapolska.logic;


import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperator {
    private static String DIRECTORY_WITH_IMAGES = "/images";
    private DirectoryCreator directoryCreator = new DirectoryCreator();
    private ImageLoader imageLoader = new ImageLoader();

    public String writeImageToFileAndGetPath(String directory, String imageAddress) {
        try {
            writeImageToFile(directory, imageAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory + DIRECTORY_WITH_IMAGES + imageLoader.getImageName(imageAddress);
    }

    private void writeImageToFile(String directory, String imageAddress) throws IOException {
        byte[] byteArray = imageLoader.getImageAsByteArray(imageLoader.getResizedImageAsBitmap(imageAddress));
        directoryCreator.createDirectory(directory, DIRECTORY_WITH_IMAGES);
        FileOutputStream fileOutputStream = new FileOutputStream(directory + DIRECTORY_WITH_IMAGES + imageLoader.getImageName(imageAddress));
        try {
            fileOutputStream.write(byteArray);
        } finally {
            fileOutputStream.close();
        }
    }
}
