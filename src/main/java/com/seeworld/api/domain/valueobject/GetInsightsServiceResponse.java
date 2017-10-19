package com.seeworld.api.domain.valueobject;

import com.ibm.watson.developer_cloud.discovery.v1.model.query.Aggregation;

import java.util.List;

public class GetInsightsServiceResponse implements IResponseMessage<UserReviewInsights> {

    private final ErrorDetails errorDetails;
    private UserReviewInsights insights;

    public GetInsightsServiceResponse(UserReviewInsights insights) {
        this.insights = insights;
        this.errorDetails = null;
    }

    public GetInsightsServiceResponse(ErrorDetails errorDetails) {
        insights = null;
        this.errorDetails = errorDetails;
    }

    @Override
    public UserReviewInsights getValue() {
        return insights;
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public boolean isSuccessful() {
        return errorDetails == null;
    }

    public void setInsights(UserReviewInsights aggregations) {
        this.insights = insights;
    }
}