package com.trzewik.quizwirtualnapolska.logic;


import android.graphics.Bitmap;

import java.io.FileOutputStream;
import java.util.logging.Logger;

public class FileOperator {
    final static private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private DirectoryCreator directoryCreator = new DirectoryCreator();

    public String getPathToImage(String appDirectory, String filesDirectory, long imageUniqueId) {
        directoryCreator.createDirectory(appDirectory, filesDirectory);
        return appDirectory + filesDirectory + "/" + imageUniqueId + ".JPEG";
    }

    public void writeImageToFile(Bitmap image, String pathToImage) {
        try {
            FileOutputStream fos = new FileOutputStream(pathToImage);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (Exception e) {
            LOGGER.info("Error when saving to internal storage! Error message: \n" + e.getMessage());
        }
    }
}
