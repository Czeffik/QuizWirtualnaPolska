package com.trzewik.quizwirtualnapolska.db.dao;


import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.trzewik.quizwirtualnapolska.db.entity.QuizAnswer;

import java.util.List;

public interface QuizAnswerDao {
    @Insert
    void insertAll(List<QuizAnswer> quizAnswers);

    @Insert
    void insert(QuizAnswer quizAnswer);

    @Update
    void update(QuizAnswer quizAnswer);

    @Delete
    void delete(QuizAnswer quizAnswer);

    @Query("SELECT * FROM quiz_answer WHERE question_id = :questionId")
    List<QuizAnswer> getAnswersByQuestionId(long questionId);
}
