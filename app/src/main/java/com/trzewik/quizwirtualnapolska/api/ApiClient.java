package com.trzewik.quizwirtualnapolska.api;



import com.trzewik.quizwirtualnapolska.model.quizDetails.QuizDetails;
import com.trzewik.quizwirtualnapolska.model.quizList.Quizzes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiClient {
    final static private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private OkHttpClient client = new OkHttpClient();

    public Quizzes getQuizzes(int startIndex, int maxResult) {
        Quizzes quizzes = null;
        try {
            quizzes =  new Quizzes(getQuizListWithCounter(startIndex, maxResult));
        } catch (JSONException|ParseException|IOException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    public QuizDetails getQuizDetails(long quizId, int startIndex)  {
        QuizDetails quizDetails = null;
        try {
            quizDetails =  new QuizDetails(getQuizDetailsJsonObject(quizId, startIndex));
        } catch (JSONException|ParseException|IOException e) {
            e.printStackTrace();
        }
        return quizDetails;
    }

    private JSONObject getQuizListWithCounter(int startIndex, int maxResult) throws IOException, JSONException {
        String url = "http://quiz.o2.pl/api/v1/quizzes/" + startIndex + '/' + maxResult;
        LOGGER.info("GET " + url);
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        LOGGER.info("Status:  " + body);
        return new JSONObject(body);
    }

    private JSONObject getQuizDetailsJsonObject(long quizId, int startIndex) throws IOException, JSONException {
        String url = "http://quiz.o2.pl/api/v1/quiz/" + quizId + '/' + startIndex;
        LOGGER.info("GET " + url);
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        LOGGER.info("Status:  " + body);
        return new JSONObject(body);
    }
}
