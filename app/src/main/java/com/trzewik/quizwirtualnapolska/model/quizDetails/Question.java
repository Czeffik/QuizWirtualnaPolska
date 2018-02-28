package com.trzewik.quizwirtualnapolska.model.quizDetails;

import com.trzewik.quizwirtualnapolska.model.quizDetails.question.Answer;
import com.trzewik.quizwirtualnapolska.model.quizDetails.question.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Question {
    private Image image;
    private List<Answer> answers;
    private String text;
    private String answer;
    private String type;
    private int order;

    public Question(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("image")) {
            this.image = new Image(jsonObject.getJSONObject("image"));
        }
        if (jsonObject.has("answers")) {
            JSONArray jsonArray = jsonObject.getJSONArray("answers");
            List<Answer> listWithAnswers = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                Answer answer = new Answer(jsonArray.getJSONObject(index));
                listWithAnswers.add(answer);
            }
            this.answers = listWithAnswers;
        }
        if (jsonObject.has("text")) {
            this.text = jsonObject.getString("text");
        }
        if (jsonObject.has("answer")) {
            this.answer = jsonObject.getString("answer");
        }
        if (jsonObject.has("type")) {
            this.type = jsonObject.getString("type");
        }
        if (jsonObject.has("order")) {
            this.order = jsonObject.getInt("order");
        }
    }

    public Image getImage() {
        return image;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public String getType() {
        return type;
    }

    public int getOrder() {
        return order;
    }
}
