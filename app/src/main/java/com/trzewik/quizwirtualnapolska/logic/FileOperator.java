package com.trzewik.quizwirtualnapolska.logic;


import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperator {
    private DirectoryCreator directoryCreator = new DirectoryCreator();
    private ImageLoader imageLoader = new ImageLoader();

    public String writeQuizImageToFileAndGetPath(String appDirectory, String filesDirectory, String imageUrlAddress, long imageUniqueId) {
        try {
            writeImageToFile(appDirectory, filesDirectory, imageUrlAddress, String.valueOf(imageUniqueId), 200, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appDirectory + filesDirectory + imageLoader.getImageName(imageUrlAddress) + imageUniqueId;
    }

    public String writeAnswerImageToFileAndGetPath(String appDirectory, String filesDirectory, String imageUrlAddress, String imageUniqueId) {
        try {
            writeImageToFile(appDirectory, filesDirectory, imageUrlAddress, imageUniqueId, 200, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appDirectory + filesDirectory + imageLoader.getImageName(imageUrlAddress) + imageUniqueId;
    }

    public String writeQuestionImageToFileAndGetPath(String appDirectory, String filesDirectory, String imageUrlAddress, String imageUniqueId) {
        try {
            writeImageToFile(appDirectory, filesDirectory, imageUrlAddress, imageUniqueId, 400, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appDirectory + filesDirectory + imageLoader.getImageName(imageUrlAddress) + imageUniqueId;
    }

    private void writeImageToFile(String appDirectory, String filesDirectory, String imageUrlAddress, String imageUniqueId, int targetWidth, int targetHeight) throws IOException {
        byte[] byteArray = imageLoader.getImageAsByteArray(imageLoader.getResizedImageAsBitmap(imageUrlAddress, targetWidth, targetHeight));
        directoryCreator.createDirectory(appDirectory, filesDirectory);
        FileOutputStream fileOutputStream = new FileOutputStream(appDirectory + filesDirectory + imageLoader.getImageName(imageUrlAddress) + imageUniqueId);
        try {
            fileOutputStream.write(byteArray);
        } finally {
            fileOutputStream.close();
        }
    }


}
