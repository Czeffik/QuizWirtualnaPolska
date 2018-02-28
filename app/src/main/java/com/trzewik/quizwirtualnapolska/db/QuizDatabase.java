package com.trzewik.quizwirtualnapolska.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import com.trzewik.quizwirtualnapolska.App;
import com.trzewik.quizwirtualnapolska.db.dao.QuizDao;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;

@Database(entities = {Quiz.class}, version =1)
public abstract class QuizDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
