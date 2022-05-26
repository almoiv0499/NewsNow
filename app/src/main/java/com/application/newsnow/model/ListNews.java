package com.application.newsnow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListNews {

    @SerializedName("articles")
    private List<News> articles;

    public ListNews() {
    }

    public ListNews(List<News> articles) {
        this.articles = articles;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
