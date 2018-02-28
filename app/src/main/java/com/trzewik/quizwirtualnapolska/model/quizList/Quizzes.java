package com.trzewik.quizwirtualnapolska.model.quizList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Quizzes {
    private int count;
    private List<Item> items;

    public Quizzes(JSONObject jsonObject) throws JSONException, ParseException {
        if (jsonObject.has("count")){
            this.count = jsonObject.getInt("count");
        }
        if (jsonObject.has("items")){
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            List<Item> listWithItems = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                Item item = new Item(jsonArray.getJSONObject(index));
                listWithItems.add(item);
            }
            this.items = listWithItems;
        }
    }

    public int getCount() {
        return count;
    }

    public List<Item> getItems() {
        return items;
    }
}
