package com.trzewik.quizwirtualnapolska;


import com.trzewik.quizwirtualnapolska.api.ApiClient;
import com.trzewik.quizwirtualnapolska.model.quizDetails.QuizDetails;
import com.trzewik.quizwirtualnapolska.model.quizList.Quizzes;

import org.apache.commons.lang3.RandomUtils;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class ApiClientTest {
    private ApiClient apiClient = new ApiClient();

    @Test
    public void counterAndQuizListShouldBeEqual() throws JSONException, ParseException, IOException {
        int startIndex = 0;
        int maxResult = 1;
        Quizzes quizzes = apiClient.getQuizzes(startIndex, maxResult);

        assert quizzes.getItems().size() == quizzes.getCount();
    }

    @Test
    public void shouldBePossibleToGetQuizDetails() throws ParseException, JSONException, InterruptedException, IOException {
        int startIndex = 0;
        int maxResult = 100;
        long quizId;

        Quizzes quizzes = apiClient.getQuizzes(startIndex, maxResult);

        quizId = quizzes.getItems().get(RandomUtils.nextInt(startIndex, maxResult)).getId();
        QuizDetails quizDetails = apiClient.getQuizDetails(quizId, startIndex);

        assert quizId == quizDetails.getId();
    }

    @Test
    public void shouldTestAll() throws ParseException, IOException, JSONException, InterruptedException {
        int startIndex = 0;
        int maxResult = 1000;
        long quizId;

        Quizzes quizzes = apiClient.getQuizzes(startIndex, maxResult);
        for (int i = startIndex; i<maxResult; i++) {
            quizId = quizzes.getItems().get(i).getId();
            QuizDetails quizDetails = apiClient.getQuizDetails(quizId, startIndex);
            Thread.sleep(200);
        }
    }
}
