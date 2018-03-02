package com.trzewik.quizwirtualnapolska.logic;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class FileOperator {
    final static private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private DirectoryCreator directoryCreator = new DirectoryCreator();

    public String writeImageToFile(String appDirectory, String filesDirectory, String imageUniqueId, byte[] byteArray)  {
        String pathToImage = appDirectory + filesDirectory +"/"+ imageUniqueId + ".JPEG";
        directoryCreator.createDirectory(appDirectory, filesDirectory);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(pathToImage);
            fileOutputStream.write(byteArray);
        } catch (IOException e) {
            LOGGER.info("FileOutputStream wasn't created!");
            e.printStackTrace();
        }  finally {
            try {
                fileOutputStream.close();
            } catch (IOException | NullPointerException e) {
                LOGGER.info("FileOutputStream wasn't closed!");
            }
        }
        return pathToImage;
    }
}
