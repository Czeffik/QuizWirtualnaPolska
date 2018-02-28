package com.trzewik.quizwirtualnapolska.logic;

import com.trzewik.quizwirtualnapolska.App;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;

import java.util.List;


public class DatabaseController {

    public void insertQuizListToDatabase(App app, List<Quiz> quizzes){
        app.getDatabase().quizDao().insertAll(quizzes);
        app.setForceUpdate(false);
    }

    public void insertQuizQuestionListToDatabase(App app, List<QuizQuestion> questions){
        app.getDatabase().quizQuestionDao().insertAll(questions);
        app.setForceUpdate(false);
    }

    public void insertQuestionAnswerListToDatabase(App app, List<QuizAnswer> quizAnswers){
        app.getDatabase().quizAnswerDao().insertAll(quizAnswers);
        app.setForceUpdate(false);
    }

    public List<Quiz> getQuizListFromDb(App app) {
        return app.getDatabase().quizDao().getAll();
    }
}
