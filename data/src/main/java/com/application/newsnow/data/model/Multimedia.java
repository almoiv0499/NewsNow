package com.application.newsnow.data.model;

import com.google.gson.annotations.SerializedName;

public class Multimedia {

    private static final String SPLIT_WORD = " for";

    @SerializedName("url")
    private String image;

    @SerializedName("copyright")
    private String author;

    public String getImage() {
        return image;
    }

    public String getAuthor() {
        return author.split(SPLIT_WORD)[0];
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
