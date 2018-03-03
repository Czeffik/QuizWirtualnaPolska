package com.trzewik.quizwirtualnapolska.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.trzewik.quizwirtualnapolska.db.entity.Rate;

import java.util.List;


@Dao
public interface RateDao {
    @Insert
    void insertAll(List<Rate> rates);

    @Update
    void update(Rate rate);

    @Delete
    void delete(Rate rate);

    @Query("SELECT * FROM rate WHERE quizId = :quizId")
    List<Rate> getAll(long quizId);

}
