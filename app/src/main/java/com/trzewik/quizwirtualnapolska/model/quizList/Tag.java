package com.trzewik.quizwirtualnapolska.model.quizList;


import org.json.JSONException;
import org.json.JSONObject;

public class Tag {
    private long uid;
    private String name;
    private String type;

    public Tag(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("uid")) {
            this.uid = jsonObject.getLong("uid");
        }
        if (jsonObject.has("name")) {
            this.name = jsonObject.get("name").toString();
        }
        if (jsonObject.has("type")) {
            this.type = jsonObject.getString("type");
        }
    }

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
