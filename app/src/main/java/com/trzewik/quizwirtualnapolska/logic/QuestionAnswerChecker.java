package com.trzewik.quizwirtualnapolska.logic;


import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;

public class QuestionAnswerChecker {

    DatabaseController databaseController = new DatabaseController();

    public void setQuestionAnswer(QuestionAnswer questionAnswer) {
        QuizQuestion quizQuestion = databaseController.getQuizQuestionById(questionAnswer.getQuestionId());
        if (checkQuestionAnswer(questionAnswer)) {
            quizQuestion.setCorrectAnswer(1);
        } else {
            quizQuestion.setCorrectAnswer(0);
        }
        quizQuestion.setAnswered(1);
        databaseController.updateQuizQuestion(quizQuestion);
    }

    public boolean checkQuestionAnswer(QuestionAnswer questionAnswer) {
        if (questionAnswer.getIsCorrect() == 1) {
            return true;
        } else {
            return false;
        }
    }
}
