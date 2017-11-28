package com.seeworld.api.controller;

import com.seeworld.api.domain.mapper.ResponseEntityMapper;
import com.seeworld.api.domain.service.IPlaceSearchService;
import com.seeworld.api.domain.valueobject.IResponseMessage;
import com.seeworld.api.domain.valueobject.PlaceSearchServiceResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("seeworld/api/v1/geo-service/")
public class PlaceSearchController {

    private final IPlaceSearchService placeSearchService;
    private final ResponseEntityMapper responseEntityMapper;

    @Autowired
    public PlaceSearchController(IPlaceSearchService placeSearchService,
                                               ResponseEntityMapper responseEntityMapper) {
        this.placeSearchService = placeSearchService;
        this.responseEntityMapper = responseEntityMapper;
    }

    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PlaceSearchServiceResponse.class),
            @ApiResponse(code = 500, message = "Error", response = PlaceSearchServiceResponse.class)})
    public ResponseEntity<? extends IResponseMessage> classify(
            @RequestParam("input") final String input,
            @RequestParam("lat") final float latitude,
            @RequestParam("lng") final float longitude) {
        PlaceSearchServiceResponse response
                = placeSearchService.search(input, latitude, longitude);
        return responseEntityMapper.mapWithRequestId(response);
    }
}
