package com.trzewik.quizwirtualnapolska.model.quizList;


import org.json.JSONException;
import org.json.JSONObject;

public class Category {
    private long id;
    private String name;

    public Category(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("id")) {
            this.id = jsonObject.getLong("id");
        }
        if (jsonObject.has("name")) {
            this.name = jsonObject.getString("name");
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
