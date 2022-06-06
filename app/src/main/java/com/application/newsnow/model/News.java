package com.application.newsnow.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class News implements Serializable {

    private static final String PATTERN_DATE_TIME = "dd.MM.yyyy HH:ss";

    @SerializedName("published_date")
    private String publishedAt;

    @SerializedName("title")
    private String title;

    @SerializedName("abstract")
    private String description;

    @SerializedName("multimedia")
    private List<Multimedia> multimedia;

    public News() {
    }

    public News(String publishedAt, String title, String description, List<Multimedia> multimedia) {
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.multimedia = multimedia;
    }

    public String getPublishedAt() {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE_TIME);
        return formatter.format(new Date());
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }
}
