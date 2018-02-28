package com.trzewik.quizwirtualnapolska.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

//(foreignKeys = {
//@ForeignKey(entity = QuizQuestion.class, parentColumns = "id", childColumns ="question_id" ),
//        })
@Entity
public class QuizAnswer {
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

    public QuizAnswer() {
    }

    @Ignore
    public QuizAnswer(String text, int isCorrect, String imagePath, long questionId) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.imagePath = imagePath;
        this.questionId = questionId;
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
}
