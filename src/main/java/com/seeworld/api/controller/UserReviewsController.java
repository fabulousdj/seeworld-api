package com.seeworld.api.controller;

import com.seeworld.api.domain.mapper.ResponseEntityMapper;
import com.seeworld.api.domain.service.IUserReviewsService;
import com.seeworld.api.domain.valueobject.*;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jidai on 9/19/17.
 * User Reviews Controller
 */

@RestController
@RequestMapping("seeworld/api/v1/user-reviews")
public class UserReviewsController {
    private final IUserReviewsService userReviewsService;
    private final ResponseEntityMapper responseEntityMapper;

    @Autowired
    public UserReviewsController(IUserReviewsService userReviewsService, ResponseEntityMapper responseEntityMapper) {
        this.userReviewsService = userReviewsService;
        this.responseEntityMapper = responseEntityMapper;
    }

    @CrossOrigin
    @RequestMapping(value = "/get-insights", method = RequestMethod.POST, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GetInsightsServiceResponse.class),
            @ApiResponse(code = 500, message = "Error", response = GetInsightsServiceResponse.class)})
    public ResponseEntity<? extends IResponseMessage> getInsights(
            @RequestBody final LocationInfo destination) {
        GetInsightsServiceResponse response = userReviewsService.getInsights(destination);
        return responseEntityMapper.mapWithRequestId(response);
    }

    @CrossOrigin
    @RequestMapping(value = "/post-review", method = RequestMethod.POST, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PostUserReviewServiceResponse.class),
            @ApiResponse(code = 500, message = "Error", response = PostUserReviewServiceResponse.class)})
    public ResponseEntity<? extends IResponseMessage> updateNavigationIndices(
            @RequestBody final UserReview review) {
        final PostUserReviewServiceResponse response = userReviewsService.postReview(review);
        return responseEntityMapper.mapWithRequestId(response);
    }
}
