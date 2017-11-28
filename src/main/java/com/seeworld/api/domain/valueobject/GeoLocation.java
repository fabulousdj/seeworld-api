package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoLocation {
    @JsonProperty("lat")
    private float latitude;
    @JsonProperty("lng")
    private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
