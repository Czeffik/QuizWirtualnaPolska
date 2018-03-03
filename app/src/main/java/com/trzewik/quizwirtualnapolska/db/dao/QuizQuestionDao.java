package com.trzewik.quizwirtualnapolska.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;

import java.util.List;

@Dao
public interface QuizQuestionDao {
    @Insert
    void insert(QuizQuestion quizQuestion);

    @Insert
    void insertAll(List<QuizQuestion> quizQuestions);

    @Update
    void update(QuizQuestion quizQuestion);

    @Delete
    void delete(QuizQuestion quizQuestion);

    @Query("SELECT * FROM quizQuestion WHERE quizId = :quizId")
    List<QuizQuestion> getQuestionsByQuizId(long quizId);

    @Query("SELECT * FROM quizQuestion WHERE quizId = :quizId AND answered = 0")
    List<QuizQuestion> getQuestionsWithoutAnswerByQuizId(long quizId);

    @Query("SELECT * FROM quizQuestion WHERE id = :questionId")
    QuizQuestion getQuestionByQuestionId(long questionId);

    @Query("UPDATE quizQuestion SET answered = 0, correctAnswer = 0 WHERE quizId = :quizId")
    void clearQuestionsAnswersByQuizId(long quizId);

    @Query("SELECT Count(*) FROM quizQuestion WHERE quizId = :quizId")
    int getNumberOfQuestions(long quizId);

    @Query("SELECT Count(*) FROM quizQuestion WHERE quizId = :quizId AND correctAnswer = 1")
    int getNumberOfCorrectAnswers(long quizId);

    @Query("SELECT Count(*) FROM quizQuestion WHERE quizId = :quizId AND answered = 1")
    int getNumberOfQuestionsWithAnswer(long quizId);

    @Query("SELECT Count(*) FROM quizQuestion WHERE quizId = :quizId AND answered = 0")
    int getNumberOfQuestionsWithoutAnswer(long quizId);
}
