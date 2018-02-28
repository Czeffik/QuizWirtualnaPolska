package com.trzewik.quizwirtualnapolska.model.quizDetails;

import org.json.JSONException;
import org.json.JSONObject;


public class SponsoredResults {
    private String imageAuthor;
    private String imageHeight;
    private String imageUrl;
    private String imageWidth;
    private String textColor;
    private String content;
    private String mainColor;
    private String imageSource;

    public SponsoredResults(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("imageAuthor")) {
            this.imageAuthor = jsonObject.getString("imageAuthor");
        }
        if (jsonObject.has("imageHeight")) {
            this.imageHeight = jsonObject.getString("imageHeight");
        }
        if (jsonObject.has("imageUrl")) {
            this.imageUrl = jsonObject.getString("imageUrl");
        }
        if (jsonObject.has("imageWidth")) {
            this.imageWidth = jsonObject.getString("imageWidth");
        }
        if (jsonObject.has("textColor")) {
            this.textColor = jsonObject.getString("textColor");
        }
        if (jsonObject.has("content")) {
            this.content = jsonObject.getString("content");
        }
        if (jsonObject.has("mainColor")) {
            this.mainColor = jsonObject.getString("mainColor");
        }
        if (jsonObject.has("imageSource")) {
            this.imageSource = jsonObject.getString("imageSource");
        }
    }

    public String getImageAuthor() {
        return imageAuthor;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getContent() {
        return content;
    }

    public String getMainColor() {
        return mainColor;
    }

    public String getImageSource() {
        return imageSource;
    }
}
