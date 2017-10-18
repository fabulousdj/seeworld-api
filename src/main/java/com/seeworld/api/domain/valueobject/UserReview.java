package com.seeworld.api.domain.valueobject;

import org.springframework.stereotype.Component;

@Component
public class UserReview {
    private LocationInfo location;
    private String review;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocationInfo getLocation() {
        return location;
    }

    public void setLocation(LocationInfo location) {
        this.location = location;
    }
}
