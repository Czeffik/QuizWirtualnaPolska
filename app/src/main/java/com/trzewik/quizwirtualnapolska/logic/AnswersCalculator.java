package com.trzewik.quizwirtualnapolska.logic;


import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;

import java.util.List;

public class AnswersCalculator {

    private DatabaseController databaseController = new DatabaseController();
    public int getPercentageOfCorrectAnswers(long quizId){
        return (getNumberOfCorrectAnswers(quizId)*100)/getNumberOfQuestions(quizId);
    }

    public int getNumberOfCorrectAnswers(long quizId){
        List<QuizQuestion> questionsWithCorrectAnswers = databaseController.getQuestionsWithCorrectAnswers(quizId);
        return questionsWithCorrectAnswers.size();
    }

    public int getNumberOfQuestions(long quizId){
        List<QuizQuestion> questions = databaseController.getQuestionsByQuizId(quizId);
        return questions.size();
    }
}
