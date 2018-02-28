package com.trzewik.quizwirtualnapolska.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class QuizQuestion {

    @PrimaryKey
    private long id;

    @ColumnInfo
    private long quizId;

    @ColumnInfo
    private String text;

    @ColumnInfo
    private int order;

    @ColumnInfo
    private int correctAnswer;

    @ColumnInfo
    private int answered;

    public QuizQuestion() {
    }

    @Ignore
    public QuizQuestion(long quizId, String text, int order, int answered, long id) {
        this.quizId = quizId;
        this.text = text;
        this.order = order;
        this.answered = answered;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getAnswered() {
        return answered;
    }

    public void setAnswered(int answered) {
        this.answered = answered;
    }
}
