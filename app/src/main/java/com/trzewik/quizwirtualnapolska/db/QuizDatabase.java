package com.trzewik.quizwirtualnapolska.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.trzewik.quizwirtualnapolska.db.dao.QuestionAnswerDao;
import com.trzewik.quizwirtualnapolska.db.dao.QuizDao;
import com.trzewik.quizwirtualnapolska.db.dao.QuizQuestionDao;
import com.trzewik.quizwirtualnapolska.db.dao.RateDao;
import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;
import com.trzewik.quizwirtualnapolska.db.entity.Rate;

@Database(entities = {Quiz.class, QuizQuestion.class, QuestionAnswer.class, Rate.class}, version =1)
public abstract class QuizDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
    public abstract QuestionAnswerDao questionAnswerDao();
    public abstract QuizQuestionDao quizQuestionDao();
    public abstract RateDao rateDao();
}
