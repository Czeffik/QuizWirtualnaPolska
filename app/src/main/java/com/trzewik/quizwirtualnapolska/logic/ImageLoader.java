package com.trzewik.quizwirtualnapolska.logic;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class ImageLoader {
    final static private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Bitmap getResizedImageAsBitmap(String imageAddress, int targetWidth, int targetHeight) {
        Bitmap bitmap = getBitmapFromURL(imageAddress);
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
        bitmap.recycle();
        return scaled;
    }

    private Bitmap getBitmapFromURL(String imageAddress) {
        try {
            URL url = new URL(imageAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            LOGGER.info("Image downloading failed!");
            e.printStackTrace();
            return null;
        }
    }
}
