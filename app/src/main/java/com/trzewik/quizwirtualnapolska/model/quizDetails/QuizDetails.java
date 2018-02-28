package com.trzewik.quizwirtualnapolska.model.quizDetails;

import com.trzewik.quizwirtualnapolska.model.quizList.Categories;
import com.trzewik.quizwirtualnapolska.model.quizList.Category;
import com.trzewik.quizwirtualnapolska.model.helper.Helper;
import com.trzewik.quizwirtualnapolska.model.quizList.MainPhoto;
import com.trzewik.quizwirtualnapolska.model.quizList.Tag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class QuizDetails {
    private Celebrity celebrity;
    private List<Rate> rates;
    private List<Question> questions;

    private Date createdAt;
    private boolean sponsored;
    private String title;
    private String type;
    private String content;
    private String buttonStart;
    private String shareTitle;
    private List<Categories> categories;
    private long id;
    private String scripts;
    private MainPhoto mainPhoto;
    private Category category;
    private boolean isBattle;
    private long created;
    private List<Tag> tags;

    private List<LatestResult> latestResults;
    private double avgResult;
    private int resultCount;
    private double cityAvg;
    private long cityTime;
    private long cityCount;
    private boolean userBattleDone;
    private SponsoredResults sponsoredResults;


    public QuizDetails(JSONObject jsonObject) throws JSONException, ParseException {
        if (jsonObject.has("celebrity")) {
            this.celebrity = new Celebrity(jsonObject.getJSONObject("celebrity"));
        }

        if (jsonObject.has("rates")) {
            JSONArray jsonArray = jsonObject.getJSONArray("rates");
            List<Rate> listWithRates = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                Rate rate = new Rate(jsonArray.getJSONObject(index));
                listWithRates.add(rate);
            }
            this.rates = listWithRates;
        }

        if (jsonObject.has("questions")) {
            JSONArray jsonArray = jsonObject.getJSONArray("questions");
            List<Question> listWithQuestions = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                Question question = new Question(jsonArray.getJSONObject(index));
                listWithQuestions.add(question);
            }
            this.questions = listWithQuestions;
        }
        if (jsonObject.has("scripts")) {
            this.scripts = jsonObject.getString("scripts");
        }
        if (jsonObject.has("created")) {
            this.created = jsonObject.getLong("created");
        }
        if (jsonObject.has("isBattle")) {
            this.isBattle = jsonObject.getBoolean("isBattle");
        }

        if (jsonObject.has("buttonStart")) {
            this.buttonStart = jsonObject.getString("buttonStart");
        }
        if (jsonObject.has("shareTitle")) {
            this.shareTitle = jsonObject.getString("shareTitle");
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

        if (jsonObject.has("latestResults")) {
            JSONArray jsonArray = jsonObject.getJSONArray("latestResults");
            List<LatestResult> listWithLatestResults = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                LatestResult latestResult = new LatestResult(jsonArray.getJSONObject(index));
                listWithLatestResults.add(latestResult);
            }
            this.latestResults = listWithLatestResults;
        }

        if (jsonObject.has("avgResult")) {
            this.avgResult = jsonObject.getDouble("avgResult");
        }

        if (jsonObject.has("resultCount")) {
            this.resultCount = jsonObject.getInt("resultCount");
        }

        if (jsonObject.has("cityAvg") && !jsonObject.isNull("cityAvg")) {
            this.cityAvg = jsonObject.getDouble("cityAvg");
        }

        if (jsonObject.has("cityTime") && !jsonObject.isNull("cityTime")) {
            this.cityTime = jsonObject.getLong("cityTime");
        }

        if (jsonObject.has("cityCount") && !jsonObject.isNull("cityCount")) {
            this.cityCount = jsonObject.getLong("cityCount");
        }

        if (jsonObject.has("userBattleDone")) {
            this.userBattleDone = jsonObject.getBoolean("userBattleDone");
        }

        if (jsonObject.has("sponsoredResults")) {
            this.sponsoredResults = new SponsoredResults(jsonObject.getJSONObject("sponsoredResults"));
        }

    }

    public Celebrity getCelebrity() {
        return celebrity;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean isSponsored() {
        return sponsored;
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

    public String getButtonStart() {
        return buttonStart;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public long getId() {
        return id;
    }

    public String getScripts() {
        return scripts;
    }

    public MainPhoto getMainPhoto() {
        return mainPhoto;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isBattle() {
        return isBattle;
    }

    public long getCreated() {
        return created;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<LatestResult> getLatestResults() {
        return latestResults;
    }

    public double getAvgResult() {
        return avgResult;
    }

    public int getResultCount() {
        return resultCount;
    }

    public double getCityAvg() {
        return cityAvg;
    }

    public long getCityTime() {
        return cityTime;
    }

    public long getCityCount() {
        return cityCount;
    }

    public boolean isUserBattleDone() {
        return userBattleDone;
    }

    public SponsoredResults getSponsoredResults() {
        return sponsoredResults;
    }
}
