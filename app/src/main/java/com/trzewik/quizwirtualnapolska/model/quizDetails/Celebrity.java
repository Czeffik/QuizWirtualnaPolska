package com.trzewik.quizwirtualnapolska.model.quizDetails;

import org.json.JSONException;
import org.json.JSONObject;


public class Celebrity {
    private String result;
    private String imageAuthor;
    private String imageHeight;
    private String imageUrl;
    private int show;
    private String name;
    private String imageTitle;
    private String imageWidth;
    private String content;
    private String imageSource;

    public Celebrity(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("result")) {
            this.result = jsonObject.getString("result");
        }
        if (jsonObject.has("imageAuthor")) {
            this.imageAuthor = jsonObject.getString("imageAuthor");
        }
        if (jsonObject.has("imageHeight")) {
            this.imageHeight = jsonObject.getString("imageHeight");
        }
        if (jsonObject.has("imageUrl")) {
            this.imageUrl = jsonObject.getString("imageUrl");
        }
        if (jsonObject.has("show")) {
            this.show = jsonObject.getInt("show");
        }
        if (jsonObject.has("name")) {
            this.name = jsonObject.getString("name");
        }
        if (jsonObject.has("imageTitle")) {
            this.imageTitle = jsonObject.getString("imageTitle");
        }
        if (jsonObject.has("imageWidth")) {
            this.imageWidth = jsonObject.getString("imageWidth");
        }
        if (jsonObject.has("content")) {
            this.content = jsonObject.getString("content");
        }
        if (jsonObject.has("imageSource")) {
            this.imageSource = jsonObject.getString("imageSource");
        }
    }

    public String getResult() {
        return result;
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

    public int getShow() {
        return show;
    }

    public String getName() {
        return name;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public String getContent() {
        return content;
    }

    public String getImageSource() {
        return imageSource;
    }
}
