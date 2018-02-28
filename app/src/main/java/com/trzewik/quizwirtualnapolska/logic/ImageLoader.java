package com.trzewik.quizwirtualnapolska.logic;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageLoader {

    public byte[] getImageFromUrl(String imageAddress) throws IOException {
        URL url = new URL(imageAddress);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        return out.toByteArray();
    }

    public void storeImage(String imageAddress, String storagePath) throws IOException {
        URL url = new URL(imageAddress);
        InputStream input = url.openStream();
        try {
            OutputStream output = new FileOutputStream(storagePath + "/" + getImageName(imageAddress));
            try {
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                    output.write(buffer, 0, bytesRead);
                }
            } finally {
                output.close();
            }
        } finally {
            input.close();
        }
    }

    private String getImageName(String imageAddress) {
        String[] splittedImageAddress = imageAddress.split("/");
        int arrayLength = splittedImageAddress.length;
        return splittedImageAddress[arrayLength - 1];
    }

}
