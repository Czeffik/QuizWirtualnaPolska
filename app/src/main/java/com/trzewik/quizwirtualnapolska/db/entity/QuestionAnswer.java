package com.trzewik.quizwirtualnapolska.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType;

@Entity
public class QuestionAnswer {
    @PrimaryKey
    private long id;

    @ColumnInfo
    private long questionId;

    @ColumnInfo
    private int order;

    @ColumnInfo
    private String text;

    @ColumnInfo
    private int isCorrect;

    @ColumnInfo
    private String pathToImage;

    @ColumnInfo
    private String answerType;

    public QuestionAnswer() {
    }

    @Ignore
    public QuestionAnswer(long id, long questionId, String text, int isCorrect, AnswerType answerType, int order, String pathToImage) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
        this.answerType = String.valueOf(answerType);
        this.order = order;
        this.pathToImage = pathToImage;
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

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
