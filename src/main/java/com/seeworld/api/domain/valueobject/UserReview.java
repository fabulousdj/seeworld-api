package com.seeworld.api.domain.valueobject;

import org.springframework.stereotype.Component;

@Component
public class UserReview {
    private PlaceInfo location;
    private String review;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public PlaceInfo getLocation() {
        return location;
    }

    public void setLocation(PlaceInfo location) {
        this.location = location;
    }
}
