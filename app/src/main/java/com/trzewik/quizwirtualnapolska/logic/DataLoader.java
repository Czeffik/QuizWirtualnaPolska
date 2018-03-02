package com.trzewik.quizwirtualnapolska.logic;


import com.trzewik.quizwirtualnapolska.api.ApiClient;
import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;
import com.trzewik.quizwirtualnapolska.model.quizDetails.Question;
import com.trzewik.quizwirtualnapolska.model.quizDetails.QuizDetails;
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType;
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.QuestionType;
import com.trzewik.quizwirtualnapolska.model.quizDetails.question.Answer;
import com.trzewik.quizwirtualnapolska.model.quizList.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataLoader {
    private ApiClient apiClient = new ApiClient();
    private FileOperator fileOperator = new FileOperator();
    private DatabaseController databaseController = new DatabaseController();

    public void retrieveData(String appDirectory, int startIndex, int maxResult) {
        List<Quiz> quizzes = fetchQuizListQuizDetailsAndQuizAnswers(appDirectory, startIndex, maxResult);
        databaseController.insertQuizListToDatabase(quizzes);
    }

    private List<Quiz> fetchQuizListQuizDetailsAndQuizAnswers(String appDirectory, int startIndex, int maxResult) {
        List<Item> items = apiClient.getQuizzes(startIndex, maxResult).getItems();

        List<Quiz> quizzes = new ArrayList<>();
        for (Item item : items) {
            long itemId = item.getId();
            List<QuizQuestion> quizQuestions = fetchQuizDetailsAndQuizAnswers(appDirectory, itemId);
            String pathToImage = fileOperator.writeQuizImageToFileAndGetPath(appDirectory, "/quizImages", item.getMainPhoto().getUrl(), itemId);
            Quiz quiz = new Quiz(itemId, item.getTitle(), pathToImage, item.getContent(), quizQuestions.size());
            quizzes.add(quiz);
            databaseController.insertQuizQuestionListToDatabase(quizQuestions);
        }
        return quizzes;
    }

    private List<QuizQuestion> fetchQuizDetailsAndQuizAnswers(String appDirectory, long quizId) {
        QuizDetails quizDetails = apiClient.getQuizDetails(quizId, 0);
        List<Question> questions = quizDetails.getQuestions();
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        for (Question question : questions) {
            long questionId = UUID.randomUUID().getMostSignificantBits();
            List<QuestionAnswer> questionAnswers = fetchQuizAnswers(appDirectory, question, questionId);
            String pathToImage = "";
            if (question.getType() == QuestionType.QUESTION_TEXT_IMAGE) {
                pathToImage = fileOperator.writeQuestionImageToFileAndGetPath(appDirectory, "/questionImages", question.getImage().getUrl(), questionId);
            }
            QuizQuestion quizQuestion = new QuizQuestion(quizId, question.getText(), question.getOrder(), questionId, question.getType(), pathToImage);
            quizQuestions.add(quizQuestion);
            databaseController.insertQuestionAnswerListToDatabase(questionAnswers);
        }
        return quizQuestions;
    }

    private List<QuestionAnswer> fetchQuizAnswers(String appDirectory, Question question, long questionId) {
        List<Answer> answers = question.getAnswers();
        List<QuestionAnswer> questionAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            long imageId = UUID.randomUUID().getMostSignificantBits();
            String pathToImage = "";
            if (question.getAnswer() == AnswerType.ANSWER_IMAGE) {
                pathToImage = fileOperator.writeAnswerImageToFileAndGetPath(appDirectory, "/answerImages", answer.getImage().getUrl(), imageId);
            }
            QuestionAnswer questionAnswer = new QuestionAnswer(answer.getText(), answer.isCorrect(), pathToImage, questionId, question.getAnswer());
            questionAnswers.add(questionAnswer);
        }
        return questionAnswers;
    }

}
