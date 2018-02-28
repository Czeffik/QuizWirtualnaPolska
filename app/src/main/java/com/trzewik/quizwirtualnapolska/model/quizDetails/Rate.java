package com.trzewik.quizwirtualnapolska.model.quizDetails;

import org.json.JSONException;
import org.json.JSONObject;


public class Rate {
    private int from;
    private int to;
    private String content;

    public Rate(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("from")) {
            this.from = jsonObject.getInt("from");
        }
        if (jsonObject.has("to")) {
            this.to = jsonObject.getInt("to");
        }
        if (jsonObject.has("content")) {
            this.content = jsonObject.getString("content");
        }
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }
}
