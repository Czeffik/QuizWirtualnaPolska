package com.trzewik.quizwirtualnapolska.model.quizDetails.questionAnswers;


import org.json.JSONException;
import org.json.JSONObject;

public class Image {
    private String author;
    private String width;
    private String mediaId;
    private String source;
    private String url;
    private String height;

    public Image(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("author")) {
            this.author = jsonObject.get("author").toString();
        }
        if (jsonObject.has("width")) {
            this.width = jsonObject.get("width").toString();
        }
        if (jsonObject.has("mediaId")) {
            this.mediaId = jsonObject.get("mediaId").toString();
        }
        if (jsonObject.has("source")) {
            this.source = jsonObject.get("source").toString();
        }
        if (jsonObject.has("url")) {
            this.url = jsonObject.get("url").toString();
        }
        if (jsonObject.has("height")) {
            this.height = jsonObject.get("height").toString();
        }
    }

    public String getAuthor() {
        return author;
    }

    public String getWidth() {
        return width;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public String getHeight() {
        return height;
    }
}
