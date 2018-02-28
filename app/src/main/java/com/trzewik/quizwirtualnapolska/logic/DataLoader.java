package com.trzewik.quizwirtualnapolska.logic;


import com.trzewik.quizwirtualnapolska.App;
import com.trzewik.quizwirtualnapolska.api.ApiClient;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;
import com.trzewik.quizwirtualnapolska.model.quizDetails.Question;
import com.trzewik.quizwirtualnapolska.model.quizDetails.QuizDetails;
import com.trzewik.quizwirtualnapolska.model.quizDetails.question.Answer;
import com.trzewik.quizwirtualnapolska.model.quizList.Item;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataLoader {
    private ApiClient apiClient = new ApiClient();
    private FileOperator fileOperator = new FileOperator();
    private DatabaseController databaseController = new DatabaseController();

    public void retrieveQuizList(App app, String appDirectory, int startIndex, int maxResult) throws IOException {
        List<Item> items = null;
        try {
            items = apiClient.getQuizzes(startIndex, maxResult).getItems();
        } catch (JSONException | ParseException | IOException e) {
            e.printStackTrace();
        }
        assert items != null;

        List<Quiz> quizzes = new ArrayList<>();
        for (Item item : items) {
            retrieveQuizDetails(app, appDirectory, item.getId());
            Quiz quiz = new Quiz(item.getId(), item.getTitle(), fileOperator.writeImageToFileAndGetPath(appDirectory, "/images", item.getMainPhoto().getUrl()), item.getContent());
            quizzes.add(quiz);
        }

        databaseController.insertQuizListToDatabase(app, quizzes);
    }

    private void retrieveQuizDetails(App app, String appDirectory, long quizId) {
        QuizDetails quizDetails = null;
        try {
            quizDetails = apiClient.getQuizDetails(quizId, 0);
        } catch (JSONException | ParseException | IOException e) {
            e.printStackTrace();
        }
        assert quizDetails != null;
        List<Question> questions = quizDetails.getQuestions();

        List<QuizQuestion> quizQuestions = new ArrayList<>();
        for (Question question : questions) {
            long questionId = UUID.randomUUID().getMostSignificantBits();
            retrieveQuizAnswers(app, appDirectory, question, questionId);
            QuizQuestion quizQuestion = new QuizQuestion(quizId, question.getText(), question.getOrder(), 0, questionId);
            quizQuestions.add(quizQuestion);
        }
        databaseController.insertQuizQuestionListToDatabase(app, quizQuestions);
    }

    private void retrieveQuizAnswers(App app, String appDirectory, Question question, long questionId) {
        List<Answer> answers = question.getAnswers();
        List<QuizAnswer> quizAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            String pathToImage = null;
            if (answer.getImage().getUrl().length() > 0) {
                pathToImage = fileOperator.writeImageToFileAndGetPath(appDirectory, "/answers", answer.getImage().getUrl());
            }
            QuizAnswer quizAnswer = new QuizAnswer(answer.getText(), answer.isCorrect(), pathToImage, questionId);
            quizAnswers.add(quizAnswer);
        }
        databaseController.insertQuestionAnswerListToDatabase(app, quizAnswers);
    }

}
