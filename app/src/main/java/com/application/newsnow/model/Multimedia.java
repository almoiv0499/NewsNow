package com.application.newsnow.model;

import com.google.gson.annotations.SerializedName;

public class Multimedia {

    @SerializedName("url")
    private String image;

    @SerializedName("copyright")
    private String author;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
