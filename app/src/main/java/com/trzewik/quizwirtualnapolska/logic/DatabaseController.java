package com.trzewik.quizwirtualnapolska.logic;

import com.trzewik.quizwirtualnapolska.App;
import com.trzewik.quizwirtualnapolska.db.QuizDatabase;
import com.trzewik.quizwirtualnapolska.db.dao.QuestionAnswerDao;
import com.trzewik.quizwirtualnapolska.db.dao.QuizDao;
import com.trzewik.quizwirtualnapolska.db.dao.QuizQuestionDao;
import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;

import java.util.List;


public class DatabaseController {
    private App app = App.get();
    private QuizDatabase database = app.getDatabase();
    private QuizDao quizTable = database.quizDao();
    private QuizQuestionDao questionTable = database.quizQuestionDao();
    private QuestionAnswerDao answerTable = database.questionAnswerDao();

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

    public List<Quiz> getQuizListFromDb() {
        return quizTable.getAll();
    }

    public List<QuizQuestion> getQuizQuestionsByQuizId(long id) {
        return questionTable.getNotAnsweredQuestionsByQuizId(id);
    }

    public Quiz getQuizById(long id) {
        return quizTable.getQuizById(id);
    }

    public List<QuestionAnswer> getQuizAnswerByQuestionId(long id) {
        return answerTable.getAnswersByQuestionId(id);
    }

    public QuizQuestion getQuizQuestionByQuestionId(long questionId) {
        return questionTable.getQuizQuestionByQuestionId(questionId);
    }

    public QuestionAnswer getAnswerByAnswerId(long answerId) {
        return answerTable.getQuestionAnswerByAnswerId(answerId);
    }

    public void setQuestionAnswer(long quizQuestionId, long answerId) {
        QuizQuestion quizQuestion = getQuizQuestionByQuestionId(quizQuestionId);
        QuestionAnswer questionAnswer = getAnswerByAnswerId(answerId);
        if (questionAnswer.getIsCorrect() == 1) {
            quizQuestion.setCorrectAnswer(1);
            quizQuestion.setAnswered(1);
        } else {
            quizQuestion.setCorrectAnswer(0);
            quizQuestion.setAnswered(1);
        }
        questionTable.update(quizQuestion);
    }

    public List<QuizQuestion> getQuestionsWithCorrectAnswers(long quizId){
        return questionTable.getQuizQuestionsWithCorrectAnswersByQuizId(quizId);
    }

    public List<QuizQuestion> getQuestionsByQuizId(long quizId){
        return questionTable.getQuestionsByQuizId(quizId);
    }

    public void clearQuizAnswers(long quizId){
        questionTable.clearQuizAnswersByQuizId(quizId);
    }
}
