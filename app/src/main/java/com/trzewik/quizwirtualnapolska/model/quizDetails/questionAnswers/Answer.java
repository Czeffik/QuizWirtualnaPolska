package com.trzewik.quizwirtualnapolska.model.quizDetails.questionAnswers;


import org.json.JSONException;
import org.json.JSONObject;

public class Answer {
    private Image image;
    private int order;
    private String text;
    private int isCorrect;


    public Answer(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("image")) {
            this.image = new Image(jsonObject.getJSONObject("image"));
        }
        if (jsonObject.has("order")) {
            this.order = jsonObject.getInt("order");
        }
        if (jsonObject.has("text")) {
            this.text = jsonObject.get("text").toString();
        }
        if (jsonObject.has("isCorrect")) {
            this.isCorrect = jsonObject.getInt("isCorrect");
        }
    }

    public Image getImage() {
        return image;
    }

    public int getOrder() {
        return order;
    }

    public String getText() {
        return text;
    }

    public int isCorrect() {
        return isCorrect;
    }
}
