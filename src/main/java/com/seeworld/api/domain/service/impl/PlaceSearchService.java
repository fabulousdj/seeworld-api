package com.seeworld.api.domain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seeworld.api.diagnostics.SystemEvent;
import com.seeworld.api.domain.service.IPlaceSearchService;
import com.seeworld.api.domain.valueobject.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PlaceSearchService implements IPlaceSearchService {

    private static int SEARCH_RADIUS = 50000;

    @Value("${googlePlacesApi.apiKey}")
    private String apiKey;
    @Value("${googlePlacesApi.placeSearch.endpoint}")
    private String placeSearchEndpoint;
    @Value("${googlePlacesApi.placeDetails.endpoint}")
    private String placeDetailsEndpoint;

    @Override
    public PlaceSearchServiceResponse search(String input, float latitude, float longitude) {
        String url = placeSearchEndpoint + "?input=" + input + "&location=" + latitude + "," + longitude +
                "&radius=" + SEARCH_RADIUS + "&key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
            return new PlaceSearchServiceResponse(new ErrorDetails(SystemEvent.HYSTRIX_BAD_REQUEST_ERROR.getId(),
                    "Google Places API place search service bad request."));
        }

        List<PlaceInfo> placeSearchResults = getPlaceSummary(response.getBody());
        return new PlaceSearchServiceResponse(placeSearchResults);
    }

    private List<PlaceInfo> getPlaceSummary(Map responseBody) {
        List<PlaceInfo> placeSearchResults = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List predictions = objectMapper.convertValue(responseBody.get("results"), List.class);
        for (Object prediction : predictions) {
            Map placeInfo = (Map)prediction;
            String address = (String) placeInfo.get("formatted_address");
            String name = (String) placeInfo.get("name");
            String placeId = (String) placeInfo.get("place_id");
            Map geometry = (Map) placeInfo.get("geometry");
            GeoLocation geoLocation = objectMapper.convertValue(geometry.get("location"), GeoLocation.class);
            PlaceInfo place = new PlaceInfo(name, address, placeId, geoLocation);
            placeSearchResults.add(place);
        }
        return placeSearchResults;
    }
}
