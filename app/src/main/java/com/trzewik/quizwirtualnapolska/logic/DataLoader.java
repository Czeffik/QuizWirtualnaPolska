package com.trzewik.quizwirtualnapolska.logic;


import android.graphics.Bitmap;

import com.trzewik.quizwirtualnapolska.api.ApiClient;
import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;
import com.trzewik.quizwirtualnapolska.model.quizDetails.Question;
import com.trzewik.quizwirtualnapolska.model.quizDetails.QuizDetails;
import com.trzewik.quizwirtualnapolska.model.quizDetails.Rate;
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType;
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.QuestionType;
import com.trzewik.quizwirtualnapolska.model.quizDetails.questionAnswers.Answer;
import com.trzewik.quizwirtualnapolska.model.quizList.QuizItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType.ANSWER_IMAGE;
import static com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType.ANSWER_TEXT_IMAGE;
import static com.trzewik.quizwirtualnapolska.model.quizDetails.enums.QuestionType.QUESTION_TEXT_IMAGE;

public class DataLoader {
    final static private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private FileOperator fileOperator = new FileOperator();
    private ImageLoader imageLoader = new ImageLoader();
    private DatabaseController databaseController = new DatabaseController();
    private ApiClient apiClient = new ApiClient();

    public void fetchAdditionalQuizzes(String appDirectory, int startIndex, int numberOfAvailableCores) {
        List<QuizItem> quizItems = apiClient.getQuizzes(startIndex, numberOfAvailableCores).getQuizItems();
        for (int i = 0; i < quizItems.size(); i++) {
            if (databaseController.getQuizById(quizItems.get(i).getId()) != null) {
                quizItems.remove(i);
            }
        }
        fetchQuizzesQuizDetailsAndQuizAnswers(appDirectory, quizItems, quizItems.size());
    }

    public void fetchData(String appDirectory, int startIndex, int numberOfAvailableCores) {
        List<QuizItem> quizItems = apiClient.getQuizzes(startIndex, numberOfAvailableCores).getQuizItems();
        fetchQuizzesQuizDetailsAndQuizAnswers(appDirectory, quizItems, numberOfAvailableCores);
    }

    private void fetchQuizzesQuizDetailsAndQuizAnswers(final String appDirectory, List<QuizItem> quizItems, int numberOfAvailableCores) {
        final List<Quiz> quizzes = new ArrayList<>();
        for (final QuizItem quizItem : quizItems) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long quizItemId = quizItem.getId();
                    String title = quizItem.getTitle();
                    String imageUrl = quizItem.getMainPhoto().getUrl();
                    String content = quizItem.getContent();
                    int numberOfQuestions = quizItem.getNumberOfQuestions();
                    String pathToImage = saveImageToFile(appDirectory, imageUrl, "/quizImages", quizItemId, 240, 180);
                    Quiz quiz = new Quiz(quizItemId, title, content, numberOfQuestions, pathToImage);
                    quizzes.add(quiz);
                    fetchQuizDetailsAndQuizAnswers(appDirectory, quizItemId);
                }
            }).start();
        }
        waitFor(quizzes, numberOfAvailableCores);
        databaseController.insertQuizListToDatabase(quizzes);
    }

    private void fetchQuizDetailsAndQuizAnswers(String appDirectory, long quizId) {
        QuizDetails quizDetails = apiClient.getQuizDetails(quizId, 0);
        fetchRates(quizId, quizDetails);
        List<Question> questions = quizDetails.getQuestions();
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        for (Question question : questions) {
            long questionId = Math.abs(UUID.randomUUID().getMostSignificantBits());
            String text = question.getText();
            int order = question.getOrder();
            QuestionType questionType = question.getType();
            String imageUrl = question.getImage().getUrl();
            String pathToImage = "";
            if (questionType == QUESTION_TEXT_IMAGE) {
                pathToImage = saveImageToFile(appDirectory, imageUrl, "/questionsImages", questionId, 450, 350);
            }
            QuizQuestion quizQuestion = new QuizQuestion(quizId, questionId, text, order, questionType, pathToImage);
            quizQuestions.add(quizQuestion);
            fetchQuizAnswers(appDirectory, question, questionId);
        }
        databaseController.insertQuizQuestionListToDatabase(quizQuestions);
    }

    private void fetchQuizAnswers(String appDirectory, Question question, long questionId) {
        List<Answer> answers = question.getAnswers();
        List<QuestionAnswer> questionAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            long answerId = Math.abs(UUID.randomUUID().getMostSignificantBits());
            String text = answer.getText();
            int isCorrect = answer.isCorrect();
            AnswerType answerType = question.getAnswer();
            int order = answer.getOrder();
            String imageUrl = answer.getImage().getUrl();
            String pathToImage = "";
            if (answerType == ANSWER_IMAGE || answerType == ANSWER_TEXT_IMAGE) {
                pathToImage = saveImageToFile(appDirectory, imageUrl, "/answersImages", answerId, 200, 200);
            }
            QuestionAnswer questionAnswer = new QuestionAnswer(answerId, questionId, text, isCorrect, answerType, order, pathToImage);
            questionAnswers.add(questionAnswer);
        }
        databaseController.insertQuestionAnswerListToDatabase(questionAnswers);
    }

    private void fetchRates(long quizId, QuizDetails quizDetails) {
        List<Rate> rates = quizDetails.getRates();
        List<com.trzewik.quizwirtualnapolska.db.entity.Rate> ratesToDb = new ArrayList<>();
        for (Rate rate : rates) {
            com.trzewik.quizwirtualnapolska.db.entity.Rate rateDb = new com.trzewik.quizwirtualnapolska.db.entity.Rate(quizId, rate.getFrom(), rate.getTo(), rate.getContent());
            ratesToDb.add(rateDb);
        }
        databaseController.insertRatesListToDatabase(ratesToDb);
    }

    private String saveImageToFile(String appDirectory, String imageUrl, String filesDirectory, long uniqueId, int imageTargetWidth, int imageTargetHeight) {
        Bitmap resizedImage = imageLoader.getResizedImageAsBitmap(imageUrl, imageTargetWidth, imageTargetHeight);
        String pathToFile = fileOperator.getPathToImage(appDirectory, filesDirectory, uniqueId);
        fileOperator.writeImageToFile(resizedImage, pathToFile);
        resizedImage.recycle();
        return pathToFile;
    }

    private void waitFor(List<Quiz> quizzes, int numberOfQuizzes) {
        while (quizzes.size() < numberOfQuizzes) {
            LOGGER.info("Waiting for thread synchronization! Number of quizzes: " + quizzes.size() + " should be: " + numberOfQuizzes + " quizzes");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitFor(quizzes, numberOfQuizzes);
        }
    }
}
