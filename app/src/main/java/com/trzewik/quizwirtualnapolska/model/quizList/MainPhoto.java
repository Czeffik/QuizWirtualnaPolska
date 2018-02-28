package com.trzewik.quizwirtualnapolska.model.quizList;


import org.json.JSONException;
import org.json.JSONObject;

public class MainPhoto {
    private String author;
    private long width;
    private String source;
    private String title;
    private String url;
    private long height;

    public MainPhoto(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("author")) {
            this.author = jsonObject.get("author").toString();
        }
        if (jsonObject.has("width")) {
            this.width = jsonObject.getLong("width");
        }
        if (jsonObject.has("source")) {
            this.source = jsonObject.getString("source");
        }
        if (jsonObject.has("title")) {
            this.title = jsonObject.getString("title");
        }
        if (jsonObject.has("url")) {
            this.url = jsonObject.getString("url");
        }
        if (jsonObject.has("height")) {
            this.height = jsonObject.getLong("height");
        }
    }

    public String getAuthor() {
        return author;
    }

    public long getWidth() {
        return width;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public long getHeight() {
        return height;
    }
}
