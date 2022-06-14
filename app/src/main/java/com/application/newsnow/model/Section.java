package com.application.newsnow.model;

import java.io.Serializable;

public class Section implements Serializable {

    private String image;
    private String section;

    public Section(String image, String section) {
        this.image = image;
        this.section = section;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
