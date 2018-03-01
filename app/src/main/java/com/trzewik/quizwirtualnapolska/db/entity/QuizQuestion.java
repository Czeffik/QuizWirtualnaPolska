package com.trzewik.quizwirtualnapolska.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType;
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.QuestionType;

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

    @ColumnInfo
    private String questionType;

    @ColumnInfo
    private String answerType;

    @ColumnInfo
    private String pathToImage;

    public QuizQuestion() {
    }

    @Ignore
    public QuizQuestion(long quizId, String text, int order, int answered, long id, QuestionType questionType, AnswerType answerType, String pathToImage) {
        this.quizId = quizId;
        this.text = text;
        this.order = order;
        this.answered = answered;
        this.id = id;
        this.answerType = String.valueOf(answerType);
        this.questionType = String.valueOf(questionType);
        this.pathToImage = pathToImage;
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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
