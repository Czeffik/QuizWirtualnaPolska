package com.trzewik.quizwirtualnapolska.model.quizDetails;


import com.trzewik.quizwirtualnapolska.model.helper.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

public class LatestResult {
    private long city;
    private Date endDate;
    private double result;
    private long resolveTime;

    public LatestResult(JSONObject jsonObject) throws JSONException, ParseException {
        if (jsonObject.has("city")) {
            this.city = jsonObject.getLong("city");
        }
        if (jsonObject.has("end_date")) {
            Helper helper = new Helper();
            this.endDate = helper.getEndDate(jsonObject.getString("end_date"));
        }
        if (jsonObject.has("result")) {
            this.result = jsonObject.getDouble("result");
        }
        if (jsonObject.has("resolveTime")) {
            this.resolveTime = jsonObject.getLong("resolveTime");
        }
    }

    public long getCity() {
        return city;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getResult() {
        return result;
    }

    public long getResolveTime() {
        return resolveTime;
    }
}
