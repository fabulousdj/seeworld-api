package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

@Component
public class UserReview {
    @SerializedName("place_id")
    @JsonProperty("place_id")
    private String placeId;
    private String review;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
