package com.seeworld.api.domain.service;

import com.seeworld.api.domain.valueobject.GetInsightsServiceResponse;
import com.seeworld.api.domain.valueobject.PlaceInfo;
import com.seeworld.api.domain.valueobject.UserReview;
import com.seeworld.api.domain.valueobject.PostUserReviewServiceResponse;

public interface IUserReviewsService {
    GetInsightsServiceResponse getInsights(String placeId);
    PostUserReviewServiceResponse postReview(UserReview review);
}
