package com.application.newsnow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListNews {

    @SerializedName("results")
    private List<News> results;

    public ListNews() {
    }

    public ListNews(List<News> results) {
        this.results = results;
    }

    public List<News> getResults() {
        return results;
    }

    public void setResults(List<News> results) {
        this.results = results;
    }
}
