package com.trzewik.quizwirtualnapolska.logic;


public class PercentageCalculator {

    private DatabaseController databaseController = new DatabaseController();

    public int getPercentageOfCorrectAnswers(long quizId) {
        return (databaseController.getNumberOfCorrectAnswers(quizId) * 100) / databaseController.getNumberOfQuestions(quizId);
    }

    public int getPercentOfQuizCompletion(long quizId){
        return (databaseController.getNumberOfQuestionsWithAnswer(quizId) * 100) / databaseController.getNumberOfQuestions(quizId);
    }
}
