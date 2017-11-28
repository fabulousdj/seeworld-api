package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceInfo {
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("geo_location")
    private GeoLocation geoLocation;

    public PlaceInfo(String name, String address, String placeId, GeoLocation geoLocation) {
        this.name = name;
        this.address = address;
        this.placeId = placeId;
        this.geoLocation = geoLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }
}
