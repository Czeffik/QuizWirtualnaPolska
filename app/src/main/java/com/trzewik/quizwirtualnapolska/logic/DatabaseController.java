package com.trzewik.quizwirtualnapolska.logic;

import com.trzewik.quizwirtualnapolska.App;
import com.trzewik.quizwirtualnapolska.db.QuizDatabase;
import com.trzewik.quizwirtualnapolska.db.dao.QuestionAnswerDao;
import com.trzewik.quizwirtualnapolska.db.dao.QuizDao;
import com.trzewik.quizwirtualnapolska.db.dao.QuizQuestionDao;
import com.trzewik.quizwirtualnapolska.db.dao.RateDao;
import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;
import com.trzewik.quizwirtualnapolska.db.entity.Rate;

import java.util.List;


public class DatabaseController {
    private App app = App.get();
    private QuizDatabase database = app.getDatabase();

    private QuizDao quizTable = database.quizDao();
    private QuizQuestionDao questionTable = database.quizQuestionDao();
    private QuestionAnswerDao answerTable = database.questionAnswerDao();
    private RateDao rateTable = database.rateDao();

    public void clearQuizAnswers(long quizId) {
        questionTable.clearQuestionsAnswersByQuizId(quizId);
    }


    public List<QuizQuestion> getNotAnsweredQuizQuestionsByQuizId(long quizId) {
        return questionTable.getQuestionsWithoutAnswerByQuizId(quizId);
    }

    public List<QuestionAnswer> getQuizAnswerByQuestionId(long questionId) {
        return answerTable.getAnswersByQuestionId(questionId);
    }


    public void updateQuiz(Quiz quiz) {
        quizTable.update(quiz);
    }

    public void updateQuizQuestion(QuizQuestion quizQuestion) {
        questionTable.update(quizQuestion);
    }

    public void updateQuestionAnswer(QuestionAnswer questionAnswer) {
        answerTable.update(questionAnswer);
    }


    public int getNumberOfCorrectAnswers(long quizId) {
        return questionTable.getNumberOfCorrectAnswers(quizId);
    }

    public int getNumberOfQuestionsWithoutAnswer(long quizId) {
        return questionTable.getNumberOfQuestionsWithoutAnswer(quizId);
    }

    public int getNumberOfQuestionsWithAnswer(long quizId) {
        return questionTable.getNumberOfQuestionsWithAnswer(quizId);
    }

    public List<QuizQuestion> getQuizQuestionsByQuizId(long quizId) {
        return questionTable.getQuestionsByQuizId(quizId);
    }


    public Quiz getQuizById(long id) {
        return quizTable.getQuizById(id);
    }

    public QuizQuestion getQuizQuestionById(long id) {
        return questionTable.getQuestionByQuestionId(id);
    }

    public QuestionAnswer getAnswerById(long id) {
        return answerTable.getQuestionAnswerByAnswerId(id);
    }


    public List<Rate> getAllRates(long quizId) {
        return rateTable.getAll(quizId);
    }

    public List<Quiz> getAllQuizzes() {
        return quizTable.getAll();
    }


    public int getNumberOfAnswers(long questionId) {
        return answerTable.getNumberOfAnswers(questionId);
    }

    public int getNumberOfQuestions(long quizId) {
        return questionTable.getNumberOfQuestions(quizId);
    }

    public int getNumberOfQuizzes() {
        return quizTable.getNumberOfRows();
    }


    public void insertRatesListToDatabase(List<Rate> rates) {
        rateTable.insertAll(rates);
        app.setForceUpdate(false);
    }

    public void insertQuizListToDatabase(List<Quiz> quizzes) {
        quizTable.insertAll(quizzes);
        app.setForceUpdate(false);
    }

    public void insertQuizQuestionListToDatabase(List<QuizQuestion> questions) {
        questionTable.insertAll(questions);
        app.setForceUpdate(false);
    }

    public void insertQuestionAnswerListToDatabase(List<QuestionAnswer> questionAnswers) {
        answerTable.insertAll(questionAnswers);
        app.setForceUpdate(false);
    }
}
