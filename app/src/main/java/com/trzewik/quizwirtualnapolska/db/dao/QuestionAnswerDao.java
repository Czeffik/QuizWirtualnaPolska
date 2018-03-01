package com.trzewik.quizwirtualnapolska.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;

import java.util.List;

@Dao
public interface QuestionAnswerDao {
    @Insert
    void insertAll(List<QuestionAnswer> questionAnswers);

    @Insert
    void insert(QuestionAnswer questionAnswer);

    @Update
    void update(QuestionAnswer questionAnswer);

    @Delete
    void delete(QuestionAnswer questionAnswer);

    @Query("SELECT * FROM QuestionAnswer WHERE questionId = :questionId")
    List<QuestionAnswer> getAnswersByQuestionId(long questionId);

    @Query("SELECT * FROM QuestionAnswer WHERE id = :id")
    QuestionAnswer getQuestionAnswerByAnswerId(long id);
}
