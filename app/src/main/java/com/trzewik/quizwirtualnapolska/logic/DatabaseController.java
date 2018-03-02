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
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType;
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.QuestionType;

import java.util.List;


public class DatabaseController {
    private App app = App.get();
    private QuizDatabase database = app.getDatabase();

    private QuizDao quizTable = database.quizDao();
    private QuizQuestionDao questionTable = database.quizQuestionDao();
    private QuestionAnswerDao answerTable = database.questionAnswerDao();
    private RateDao rateTable = database.rateDao();

    private ImageLoader imageLoader = new ImageLoader();
    private FileOperator fileOperator = new FileOperator();

    public void insertRatesListToDatabase(List<Rate> rates) {
        rateTable.insertAll(rates);
    }

    public List<Rate> getAllRates() {
        return rateTable.getAll();
    }

    public String getRateMessage(int result) {
        List<Rate> rates = getAllRates();
        String message = null;
        for (Rate rate : rates) {
            if (result >= rate.getValueFrom() && result <= rate.getValueTo()) {
                message = rate.getMessage();
            }
        }
        return message;
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

    public int getNumberOfQuizzes() {
        return quizTable.getNumberOfRows();
    }

    public List<Quiz> getQuizListFromDb() {
        return quizTable.getAll();
    }

    public List<QuizQuestion> getNotAnsweredQuizQuestionsByQuizId(long quizId) {
        return questionTable.getNotAnsweredQuestionsByQuizId(quizId);
    }

    public Quiz getQuizById(long id) {
        return quizTable.getQuizById(id);
    }

    public List<QuestionAnswer> getQuizAnswerByQuestionId(long id) {
        return answerTable.getAnswersByQuestionId(id);
    }

    public void setQuestionAnswer(long quizQuestionId, long answerId) {
        QuizQuestion quizQuestion = getQuizQuestionByQuestionId(quizQuestionId);
        Quiz quiz = getQuizById(quizQuestion.getQuizId());
        QuestionAnswer questionAnswer = getAnswerByAnswerId(answerId);
        if (questionAnswer.getIsCorrect() == 1) {
            quizQuestion.setCorrectAnswer(1);
            quiz.setCorrectAnswers(quiz.getCorrectAnswers() + 1);
        } else {
            quizQuestion.setCorrectAnswer(0);
        }
        quizQuestion.setAnswered(1);
        quiz.setLastResult(quiz.getLastResult() + 1);
        quizTable.update(quiz);
        questionTable.update(quizQuestion);
    }

    public void clearQuizAnswers(long quizId) {
        Quiz quiz = getQuizById(quizId);
        quiz.setCorrectAnswers(0);
        quiz.setLastResult(0);
        quizTable.update(quiz);
        questionTable.clearQuizAnswersByQuizId(quizId);
    }

    public List<QuizQuestion> getQuestionsWithCorrectAnswers(long quizId) {
        return questionTable.getQuizQuestionsWithCorrectAnswersByQuizId(quizId);
    }

    public List<QuizQuestion> getQuestionsByQuizId(long quizId) {
        return questionTable.getQuestionsByQuizId(quizId);
    }

    public QuizQuestion getQuizQuestionByQuestionId(long questionId) {
        return questionTable.getQuizQuestionByQuestionId(questionId);
    }

    public QuestionAnswer getAnswerByAnswerId(long answerId) {
        return answerTable.getQuestionAnswerByAnswerId(answerId);
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

    public void setPathToQuizImageInDatabase(List<Quiz> quizzes, String appDirectory) {
        for (Quiz quiz : quizzes) {
            long quizId = quiz.getId();
            String imageUrl = quiz.getImageUrl();
            byte[] resizedImage = imageLoader.getImageAsByteArray(imageLoader.getResizedImageAsBitmap(imageUrl, 300, 300));
            String pathToFile = fileOperator.writeImageToFile(appDirectory, "/quizImages", String.valueOf(quizId), resizedImage);
            quiz.setPathToImage(pathToFile);
            updateQuiz(quiz);
        }
    }

    public void setPathToQuestionImageInDatabase(List<QuizQuestion> quizQuestions, String appDirectory) {
        for (QuizQuestion question : quizQuestions) {
            if (question.getQuestionType().equals(QuestionType.QUESTION_TEXT_IMAGE.toString())) {
                long questionId = question.getId();
                String imageUrl = question.getImageUrl();
                byte[] resizedImage = imageLoader.getImageAsByteArray(imageLoader.getResizedImageAsBitmap(imageUrl, 400, 400));
                String pathToFile = fileOperator.writeImageToFile(appDirectory, "/questionImages", String.valueOf(questionId), resizedImage);
                question.setPathToImage(pathToFile);
                updateQuizQuestion(question);
            }
        }
    }

    public void setPathToAnswerImageInDatabase(List<QuestionAnswer> answers, String appDirectory) {
        for (QuestionAnswer answer : answers) {
            if (answer.getAnswerType().equals(AnswerType.ANSWER_IMAGE.toString()) || answer.getAnswerType().equals(AnswerType.ANSWER_TEXT_IMAGE.toString())) {
                String imageUrl = answer.getImageUrl();
                byte[] resizedImage = imageLoader.getImageAsByteArray(imageLoader.getResizedImageAsBitmap(imageUrl, 200, 200));
                String pathToFile = fileOperator.writeImageToFile(appDirectory, "/answerImages", String.valueOf(answer.getId()), resizedImage);
                answer.setPathToImage(pathToFile);
                updateQuestionAnswer(answer);
            }
        }
    }
}
