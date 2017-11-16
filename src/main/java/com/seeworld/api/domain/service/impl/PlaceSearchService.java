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

    @Value("${googlePlacesApi.apiKey}")
    private String apikey;
    @Value("${googlePlacesApi.placeAutocomplete.endpoint}")
    private String placeAutocompleteEndpoint;
    @Value("${googlePlacesApi.placeDetails.endpoint}")
    private String placeDetailsEndpoint;

    @Override
    public PlaceSearchServiceResponse search(String input, String location) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = placeAutocompleteEndpoint + "?input=" + input + "&location=" + location + "&language=en_US" + "&key=" + apikey;
        ResponseEntity<Map> response = restTemplate.getForEntity(requestUrl, Map.class);

        if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
            return new PlaceSearchServiceResponse(new ErrorDetails(SystemEvent.HYSTRIX_BAD_REQUEST_ERROR.getId(),
                    "Google Places API autocomplete service bad request."));
        }

        List<String> placeIds = getPlaceIds(response.getBody());
        return new PlaceSearchServiceResponse(placeIds);
    }

    private List<String> getPlaceIds(Map responseBody) {
        List<String> placeIds = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List predictions = objectMapper.convertValue(responseBody.get("predictions"), List.class);
        for (Object obj : predictions) {
            Map<String, Object> prediction = objectMapper.convertValue(obj, Map.class);
            String placeId = (String) prediction.get("place_id");
            placeIds.add(placeId);
        }
        return placeIds;
    }
}
