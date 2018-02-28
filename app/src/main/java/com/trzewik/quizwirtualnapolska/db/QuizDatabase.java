package com.trzewik.quizwirtualnapolska.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.trzewik.quizwirtualnapolska.db.dao.QuizAnswerDao;
import com.trzewik.quizwirtualnapolska.db.dao.QuizDao;
import com.trzewik.quizwirtualnapolska.db.dao.QuizQuestionDao;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;

@Database(entities = {Quiz.class, QuizQuestion.class, QuizAnswer.class}, version =1)
public abstract class QuizDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
    public abstract QuizAnswerDao quizAnswerDao();
    public abstract QuizQuestionDao quizQuestionDao();
}
