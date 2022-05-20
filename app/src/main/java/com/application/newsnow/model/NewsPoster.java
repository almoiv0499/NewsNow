package com.application.newsnow.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsPoster implements Parcelable {

    private String section;
    private String timeAgo;
    private String posterTitle;
    private int posterImageResource;

    public NewsPoster() {
    }

    public NewsPoster(String section, String timeAgo, String posterTitle, int posterImageResource) {
        this.section = section;
        this.timeAgo = timeAgo;
        this.posterTitle = posterTitle;
        this.posterImageResource = posterImageResource;
    }

    public NewsPoster(Parcel parcel) {
        String[] data = new String[4];
        parcel.readStringArray(data);
        this.section = data[0];
        this.timeAgo = data[1];
        this.posterTitle = data[2];
        this.posterImageResource = Integer.parseInt(data[3]);
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getPosterTitle() {
        return posterTitle;
    }

    public void setPosterTitle(String posterTitle) {
        this.posterTitle = posterTitle;
    }

    public int getPosterImageResource() {
        return posterImageResource;
    }

    public void setPosterImageResource(int posterImageResource) {
        this.posterImageResource = posterImageResource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {section, timeAgo, posterTitle, String.valueOf(posterImageResource)});
    }

    public static final Parcelable.Creator<NewsPoster> CREATOR = new Parcelable.Creator<NewsPoster>() {
        @Override
        public NewsPoster createFromParcel(Parcel parcel) {
            return new NewsPoster(parcel);
        }

        @Override
        public NewsPoster[] newArray(int i) {
            return new NewsPoster[i];
        }
    };
}
