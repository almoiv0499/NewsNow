package com.application.newsnow.model;

public class NewsPoster {

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
}
