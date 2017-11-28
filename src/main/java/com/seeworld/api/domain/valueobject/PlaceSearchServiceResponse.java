package com.seeworld.api.domain.valueobject;

import java.util.List;

public class PlaceSearchServiceResponse implements IResponseMessage<List<PlaceInfo>>  {

    private final ErrorDetails errorDetails;
    private List<PlaceInfo> placeSearchResults;

    public PlaceSearchServiceResponse(List<PlaceInfo> placeSearchResults) {
        this.placeSearchResults = placeSearchResults;
        this.errorDetails = null;
    }

    public PlaceSearchServiceResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public void setPlaceSearchResults(List<PlaceInfo> placeSearchResults) {
        this.placeSearchResults = placeSearchResults;
    }

    @Override
    public List<PlaceInfo> getValue() {
        return placeSearchResults;
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
