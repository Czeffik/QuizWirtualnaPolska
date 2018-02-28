package com.trzewik.quizwirtualnapolska.logic;


import com.trzewik.quizwirtualnapolska.App;
import com.trzewik.quizwirtualnapolska.api.ApiClient;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.model.quizList.Item;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private ApiClient apiClient = new ApiClient();
    private ImageLoader imageLoader = new ImageLoader();

    public List<Quiz> getQuizListFromDb(App app) {
        return app.getDatabase().quizDao().getAll();
    }

    public void retrieveQuizList(App app, int startIndex, int maxResult) throws IOException {
        List<Item> items = null;
        try {
            items = apiClient.getQuizzes(startIndex, maxResult).getItems();
        } catch (JSONException | ParseException | IOException e) {
            e.printStackTrace();
        }
        List<Quiz> quizzes = new ArrayList<>();
        assert items != null;
        for (Item item : items) {
            Quiz quiz = new Quiz();
            quiz.setTitle(item.getTitle());
            quiz.setId(item.getId());
            System.out.println(item.getMainPhoto().getUrl());
            quiz.setImage(imageLoader.getImageFromUrl(item.getMainPhoto().getUrl()));
            quizzes.add(quiz);
        }

        app.getDatabase().quizDao().insertAll(quizzes);
        app.setForceUpdate(false);
    }

}
