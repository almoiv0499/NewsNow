package com.application.newsnow.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class News implements Parcelable {

    @SerializedName("author")
    private String author;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("title")
    private String title;

    @SerializedName("urlToImage")
    private String urlToImage;

    public News() {
    }

    public News(String author, String publishedAt, String title, String urlToImage) {
        this.author = author;
        this.publishedAt = publishedAt;
        this.title = title;
        this.urlToImage = urlToImage;
    }

    public News(Parcel parcel) {
        String[] data = new String[3];
        parcel.readStringArray(data);
        this.author = data[0];
        this.publishedAt = data[1];
        this.title = data[2];
        this.urlToImage = data[3];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {author, publishedAt, title, urlToImage});
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel parcel) {
            return new News(parcel);
        }

        @Override
        public News[] newArray(int i) {
            return new News[i];
        }
    };
}
