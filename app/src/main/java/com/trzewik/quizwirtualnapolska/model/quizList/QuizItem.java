package com.trzewik.quizwirtualnapolska.model.quizList;


import com.trzewik.quizwirtualnapolska.model.helper.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuizItem {

    private String buttonStart;
    private String shareTitle;
    private int numberOfQuestions;
    private Date createdAt;
    private boolean sponsored;
    private List<Categories> categories;
    private long id;
    private String title;
    private String type;
    private String content;
    private MainPhoto mainPhoto;
    private Category category;
    private List<Tag> tags;

    public QuizItem(JSONObject jsonObject) throws JSONException, ParseException {
        if (jsonObject.has("buttonStart")) {
            this.buttonStart = jsonObject.getString("buttonStart");
        }
        if (jsonObject.has("shareTitle")) {
            this.shareTitle = jsonObject.getString("shareTitle");
        }
        if (jsonObject.has("questions")) {
            this.numberOfQuestions = jsonObject.getInt("questions");
        }
        if (jsonObject.has("createdAt")) {
            Helper helper = new Helper();
            this.createdAt = helper.getDate(jsonObject.getString("createdAt"));
        }
        if (jsonObject.has("sponsored")) {
            this.sponsored = jsonObject.getBoolean("sponsored");
        }
        if (jsonObject.has("id")) {
            this.id = jsonObject.getLong("id");
        }
        if (jsonObject.has("title")) {
            this.title = jsonObject.getString("title");
        }
        if (jsonObject.has("type")) {
            this.type = jsonObject.getString("type");
        }
        if (jsonObject.has("content")) {
            this.content = jsonObject.getString("content");
        }

        if (jsonObject.has("mainPhoto")) {
            this.mainPhoto = new MainPhoto(jsonObject.getJSONObject("mainPhoto"));
        }

        if (jsonObject.has("category")) {
            this.category = new Category(jsonObject.getJSONObject("category"));
        }

        if (jsonObject.has("categories")) {
            JSONArray jsonArray = jsonObject.getJSONArray("categories");
            List<Categories> listWithCategories = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                Categories categories = new Categories(jsonArray.getJSONObject(index));
                listWithCategories.add(categories);
            }
            this.categories = listWithCategories;
        }

        if (jsonObject.has("tags")) {
            JSONArray jsonArray = jsonObject.getJSONArray("tags");
            List<Tag> listWithTags = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                Tag tag = new Tag(jsonArray.getJSONObject(index));
                listWithTags.add(tag);
            }
            this.tags = listWithTags;
        }
    }

    public String getButtonStart() {
        return buttonStart;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean isSponsored() {
        return sponsored;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public MainPhoto getMainPhoto() {
        return mainPhoto;
    }

    public Category getCategory() {
        return category;
    }

    public List<Tag> getTags() {
        return tags;
    }
}
