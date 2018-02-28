package com.trzewik.quizwirtualnapolska.model.helper;

import android.annotation.SuppressLint;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    public Date getDate(String date) throws ParseException {
        return new DateTime(date).toDate();
    }

    public Date getEndDate(String date) throws ParseException {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(date);
    }
}
