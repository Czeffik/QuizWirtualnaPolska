package com.trzewik.quizwirtualnapolska;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;

import com.trzewik.quizwirtualnapolska.db.QuizDatabase;


public class App extends Application {

    public static App INSTANCE;
    private static final String DATABASE_NAME = "QuizDatabase";
    private static final String PREFERENCES = "WpQuiz.preferences";
    private static final String KEY_FORCE_UPDATE = "force_update";

    QuizDatabase database;


    public static App get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), QuizDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        INSTANCE = this;
    }

    public QuizDatabase getDatabase() {
        return database;
    }

    public boolean isForceUpdate() {
        return getSP().getBoolean(KEY_FORCE_UPDATE, true);
    }

    public void setForceUpdate(boolean force) {
        SharedPreferences.Editor edit = getSP().edit();
        edit.putBoolean(KEY_FORCE_UPDATE, force);
        edit.apply();
    }

    private SharedPreferences getSP() {
        return getSharedPreferences(PREFERENCES, MODE_PRIVATE);
    }


}
