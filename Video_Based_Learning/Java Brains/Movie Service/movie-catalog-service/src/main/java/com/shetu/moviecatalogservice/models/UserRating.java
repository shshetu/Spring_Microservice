package com.shetu.moviecatalogservice.models;

import java.util.List;

public class UserRating {
    private String userId;

    public UserRating() {
    }

    public UserRating(String userId, List<Rating> userRating) {
        this.userId = userId;
        this.userRating = userRating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private List<Rating> userRating;

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }
}
