package com.trzewik.quizwirtualnapolska.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.trzewik.quizwirtualnapolska.db.entity.Quiz;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    void insertAll(List<Quiz> quizzes);

    @Update
    void update(Quiz quiz);

    @Delete
    void delete(Quiz quiz);

    @Query("SELECT * FROM quiz")
    List<Quiz> getAll();

    @Query("SELECT * FROM quiz WHERE id = :id")
    Quiz getQuizById(long id);

}
