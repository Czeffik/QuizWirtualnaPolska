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
    void delete (QuizQuestion quizQuestion);

    @Query("SELECT * FROM quizQuestion WHERE quizId = :quizId")
    List<QuizQuestion> getQuestionsByQuizId(long quizId);

    @Query("SELECT * FROM quizQuestion WHERE quizId = :quizId AND answered = 0")
    List<QuizQuestion> getNotAnsweredQuestionsByQuizId(long quizId);

    @Query("SELECT * FROM quizquestion WHERE id = :questionId")
    QuizQuestion getQuizQuestionByQuestionId(long questionId);
}
