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
    private int resolved;

    @ColumnInfo
    private int lastResult;

    @ColumnInfo
    private int numberOfQuestions;

    @ColumnInfo
    private String imagePath;

    @ColumnInfo
    private String content;

    public Quiz() {
    }

    @Ignore
    public Quiz(long id, String title, String imagePath, String content) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.content = content;
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

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
