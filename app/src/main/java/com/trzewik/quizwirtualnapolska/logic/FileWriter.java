package com.trzewik.quizwirtualnapolska.logic;


import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

public class FileWriter {

    public void storeImage(Context context, String filename){
        File file = new File(context.getFilesDir(), filename);
    }
}
