package com.application.newsnow.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class News implements Serializable {

    @SerializedName("author")
    private String author;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("title")
    private String title;

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("description")
    private String description;

    public News() {
    }

    public News(String author, String publishedAt, String title, String urlToImage) {
        this.author = author;
        this.publishedAt = publishedAt;
        this.title = title;
        this.urlToImage = urlToImage;
    }

    public News(String author, String publishedAt, String title, String urlToImage, String description) {
        this.author = author;
        this.publishedAt = publishedAt;
        this.title = title;
        this.urlToImage = urlToImage;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
