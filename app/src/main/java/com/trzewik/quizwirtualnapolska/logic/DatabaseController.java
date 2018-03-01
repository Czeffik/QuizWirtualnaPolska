package com.trzewik.quizwirtualnapolska.logic;

import com.trzewik.quizwirtualnapolska.App;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;

import java.util.List;


public class DatabaseController {

    public void insertQuizListToDatabase(List<Quiz> quizzes){
        App.get().getDatabase().quizDao().insertAll(quizzes);
        App.get().setForceUpdate(false);
    }

    public void insertQuizQuestionListToDatabase(List<QuizQuestion> questions){
        App.get().getDatabase().quizQuestionDao().insertAll(questions);
        App.get().setForceUpdate(false);
    }

    public void insertQuestionAnswerListToDatabase(List<QuizAnswer> quizAnswers){
        App.get().getDatabase().quizAnswerDao().insertAll(quizAnswers);
        App.get().setForceUpdate(false);
    }

    public List<Quiz> getQuizListFromDb() {
        return App.get().getDatabase().quizDao().getAll();
    }

    public List<QuizQuestion> getQuizQuestionsByQuizId(long id){
        return App.get().getDatabase().quizQuestionDao().getQuestionsByQuizId(id);
    }

    public Quiz getQuizById(long id){
        return App.get().getDatabase().quizDao().getQuizById(id);
    }

    public List<QuizAnswer> getQuizAnswerById(long id){
        return App.get().getDatabase().quizAnswerDao().getAnswersByQuestionId(id);
    }
}
