package com.application.newsnow.enums;

public enum Category {

    ARTS("arts"),
    BUSSINESS("business"),
    AUTO("automobiles"),
    FASHION("fashion"),
    FOOD("food"),
    OPINION("opinion"),
    TECHNOLOGY("technology"),
    REALESTATE("realestate"),
    MOVIES("movies"),
    SPORTS("sports"),
    TOP("world");

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
