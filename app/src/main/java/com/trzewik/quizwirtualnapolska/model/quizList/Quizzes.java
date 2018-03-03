package com.trzewik.quizwirtualnapolska.model.quizList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Quizzes {
    private int count;
    private List<QuizItem> quizItems;

    public Quizzes(JSONObject jsonObject) throws JSONException, ParseException {
        if (jsonObject.has("count")){
            this.count = jsonObject.getInt("count");
        }
        if (jsonObject.has("items")){
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            List<QuizItem> listWithQuizItems = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                QuizItem quizItem = new QuizItem(jsonArray.getJSONObject(index));
                listWithQuizItems.add(quizItem);
            }
            this.quizItems = listWithQuizItems;
        }
    }

    public int getCount() {
        return count;
    }

    public List<QuizItem> getQuizItems() {
        return quizItems;
    }
}
