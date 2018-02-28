package com.trzewik.quizwirtualnapolska.model.quizList;


import org.json.JSONException;
import org.json.JSONObject;

public class Categories {
    private long uid;
    private String secondaryCid;
    private String name;
    private String type;

    public Categories(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("uid")) {
            this.uid = jsonObject.getLong("uid");
        }
        if (jsonObject.has("secondaryCid")) {
            this.secondaryCid = jsonObject.getString("secondaryCid");
        }
        if (jsonObject.has("name")) {
            this.name = jsonObject.getString("name");
        }
        if (jsonObject.has("type")) {
            this.type = jsonObject.getString("type");
        }
    }

    public long getUid() {
        return uid;
    }

    public String getSecondaryCid() {
        return secondaryCid;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
