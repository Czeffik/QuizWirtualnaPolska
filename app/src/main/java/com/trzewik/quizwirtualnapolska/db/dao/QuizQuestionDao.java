package com.trzewik.quizwirtualnapolska.db.dao;


import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;

import java.util.List;

public interface QuizQuestionDao {
    @Insert
    void insert(QuizQuestion quizQuestion);

    @Insert
    void insertAll(List<QuizQuestion> quizQuestions);

    @Update
    void update(QuizQuestion quizQuestion);

    @Delete
    void delete (QuizQuestion quizQuestion);

    @Query("SELECT * FROM quiz_question WHERE quizId = :quizId")
    List<QuizQuestion> getQuestionsByQuizId(long quizId);

    @Query("SELECT * FROM quiz_question WHERE quizId = :quizId AND answered = 0")
    List<QuizQuestion> getNotAnsweredQuestionsByQuizId(long quizId);
}
