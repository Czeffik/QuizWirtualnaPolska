package com.trzewik.quizwirtualnapolska.logic;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageLoader {
    private static int TARGET_WIDTH = 250;
    private static int TARGET_HEIGHT = 250;
    private static int QUALITY = 100;
    private static Bitmap.CompressFormat COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;

    public Bitmap getResizedImageAsBitmap(String imageAddress) {
        byte[] byteArray = null;
        try {
            byteArray = getImageByteArray(imageAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = getImageAsBitmap(byteArray);
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, TARGET_WIDTH, TARGET_HEIGHT, true);
        bitmap.recycle();
        return scaled;
    }

    public byte[] getImageAsByteArray(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(COMPRESS_FORMAT, QUALITY, stream);
        return stream.toByteArray();
    }

    public String getImageName(String imageAddress) {
        String[] splittedImageAddress = imageAddress.split("/");
        int arrayLength = splittedImageAddress.length;
        return splittedImageAddress[arrayLength - 1];
    }

    private Bitmap getImageAsBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    private byte[] getImageByteArray(String imageAddress) throws IOException {
        URL url = new URL(imageAddress);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[128];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        return out.toByteArray();
    }
}
