package com.seeworld.api.domain.service;

import com.seeworld.api.domain.valueobject.GetInsightsServiceResponse;
import com.seeworld.api.domain.valueobject.LocationInfo;
import com.seeworld.api.domain.valueobject.UserReview;
import com.seeworld.api.domain.valueobject.PostUserReviewServiceResponse;

public interface IUserReviewsService {
    GetInsightsServiceResponse getInsights(LocationInfo destination);
    PostUserReviewServiceResponse postReview(UserReview review);
}
