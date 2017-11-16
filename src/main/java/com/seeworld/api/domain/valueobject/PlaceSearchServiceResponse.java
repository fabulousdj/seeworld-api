package com.seeworld.api.domain.valueobject;

import java.util.List;

public class PlaceSearchServiceResponse implements IResponseMessage<List<String>>  {

    private final ErrorDetails errorDetails;
    private List<String> placeIds;

    public PlaceSearchServiceResponse(List<String> placeIds) {
        this.placeIds = placeIds;
        this.errorDetails = null;
    }

    public PlaceSearchServiceResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public void setPlaceIds(List<String> placeIds) {
        this.placeIds = placeIds;
    }

    @Override
    public List<String> getValue() {
        return placeIds;
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public boolean isSuccessful() {
        return errorDetails == null;
    }
}
