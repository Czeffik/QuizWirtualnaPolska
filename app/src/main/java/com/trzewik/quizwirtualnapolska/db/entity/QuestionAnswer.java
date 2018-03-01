package com.trzewik.quizwirtualnapolska.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType;

@Entity
public class QuestionAnswer {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    private long questionId;

    @ColumnInfo
    private String text;

    @ColumnInfo
    private int isCorrect;

    @ColumnInfo
    private String imagePath;

    @ColumnInfo
    private String answerType;

    public QuestionAnswer() {
    }

    @Ignore
    public QuestionAnswer(String text, int isCorrect, String imagePath, long questionId, AnswerType answerType) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.imagePath = imagePath;
        this.questionId = questionId;
        this.answerType = String.valueOf(answerType);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }
}