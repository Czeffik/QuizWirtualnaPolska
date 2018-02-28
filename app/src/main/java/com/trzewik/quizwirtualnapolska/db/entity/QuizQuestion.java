package com.trzewik.quizwirtualnapolska.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class QuizQuestion {

    @PrimaryKey
    private long id;

    @ColumnInfo
    private String title;



}
