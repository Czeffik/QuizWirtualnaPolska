package com.trzewik.quizwirtualnapolska.logic;


import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;
import com.trzewik.quizwirtualnapolska.model.quizDetails.Question;
import com.trzewik.quizwirtualnapolska.model.quizDetails.QuizDetails;
import com.trzewik.quizwirtualnapolska.model.quizDetails.Rate;
import com.trzewik.quizwirtualnapolska.model.quizDetails.question.Answer;
import com.trzewik.quizwirtualnapolska.model.quizList.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataLoader {
    private DataFetcher dataFetcher = new DataFetcher();
    private DatabaseController databaseController = new DatabaseController();

    public void retrieveData(String appDirectory, int startIndex, int maxResult) {
        fetchQuizListQuizDetailsAndQuizAnswers(appDirectory, startIndex, maxResult);
    }

    private void fetchQuizListQuizDetailsAndQuizAnswers(String appDirectory, int startIndex, int maxResult) {
        List<Item> items = dataFetcher.getItems(startIndex, maxResult);
        Map<Long, QuizDetails> quizDetailsMap = dataFetcher.getQuizDetails(items);
        fetchRates(quizDetailsMap.get(items.get(0).getId()));
        List<Quiz> quizzes = new ArrayList<>();
        for (Item item : items) {
            long itemId = item.getId();
            List<QuizQuestion> quizQuestions = fetchQuizDetailsAndQuizAnswers(appDirectory, itemId, quizDetailsMap.get(itemId));
            Quiz quiz = new Quiz(itemId, item.getTitle(), item.getMainPhoto().getUrl(), item.getContent(), quizQuestions.size());
            quizzes.add(quiz);
        }
        databaseController.insertQuizListToDatabase(quizzes);
        databaseController.setPathToQuizImageInDatabase(quizzes, appDirectory);
    }

    private List<QuizQuestion> fetchQuizDetailsAndQuizAnswers(String appDirectory, long quizId, QuizDetails quizDetails) {
        List<Question> questions = quizDetails.getQuestions();
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        for (Question question : questions) {
            long questionId = UUID.randomUUID().getMostSignificantBits();
            fetchQuizAnswers(appDirectory, question, questionId);
            QuizQuestion quizQuestion = new QuizQuestion(quizId, question.getText(), question.getOrder(), questionId, question.getType(), question.getImage().getUrl());
            quizQuestions.add(quizQuestion);
        }
        databaseController.insertQuizQuestionListToDatabase(quizQuestions);
        databaseController.setPathToQuestionImageInDatabase(quizQuestions, appDirectory);
        return quizQuestions;
    }

    private void fetchQuizAnswers(String appDirectory, Question question, long questionId) {
        List<Answer> answers = question.getAnswers();
        List<QuestionAnswer> questionAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            long answerId = UUID.randomUUID().getMostSignificantBits();
            QuestionAnswer questionAnswer = new QuestionAnswer(answerId, answer.getText(), answer.isCorrect(), answer.getImage().getUrl(), questionId, question.getAnswer());
            questionAnswers.add(questionAnswer);
        }
        databaseController.insertQuestionAnswerListToDatabase(questionAnswers);
        databaseController.setPathToAnswerImageInDatabase(questionAnswers, appDirectory);
    }

    private void fetchRates(QuizDetails quizDetails) {
        List<Rate> rates = quizDetails.getRates();
        List<com.trzewik.quizwirtualnapolska.db.entity.Rate> ratesToDb = new ArrayList<>();
        for (Rate rate : rates) {
            com.trzewik.quizwirtualnapolska.db.entity.Rate rateDb = new com.trzewik.quizwirtualnapolska.db.entity.Rate(rate.getFrom(), rate.getTo(), rate.getContent());
            ratesToDb.add(rateDb);
        }
        databaseController.insertRatesListToDatabase(ratesToDb);
    }
}
