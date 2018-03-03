package com.trzewik.quizwirtualnapolska.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Quiz {

    @PrimaryKey
    private long id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String pathToImage;

    @ColumnInfo
    private String content;

    @ColumnInfo
    private int numberOfQuestions;

    public Quiz() {
    }

    @Ignore
    public Quiz(long id, String title, String content, int numberOfQuestions, String pathToImage) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.numberOfQuestions = numberOfQuestions;
        this.pathToImage = pathToImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
