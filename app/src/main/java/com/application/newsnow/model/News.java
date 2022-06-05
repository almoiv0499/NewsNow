package com.application.newsnow.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable {

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
        return publishedAt;
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
