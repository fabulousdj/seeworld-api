package com.seeworld.api.domain.valueobject;

import com.ibm.watson.developer_cloud.discovery.v1.model.query.Aggregation;

import java.util.List;

public class GetInsightsServiceResponse implements IResponseMessage<List<Aggregation>> {

    private final ErrorDetails errorDetails;
    private List<Aggregation> aggregations;

    public GetInsightsServiceResponse(List<Aggregation> aggregations) {
        this.aggregations = aggregations;
        this.errorDetails = null;
    }

    public GetInsightsServiceResponse(ErrorDetails errorDetails) {
        aggregations = null;
        this.errorDetails = errorDetails;
    }

    @Override
    public List<Aggregation> getValue() {
        return aggregations;
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public boolean isSuccessful() {
        return errorDetails == null;
    }

    public void setAggregations(List<Aggregation> aggregations) {
        this.aggregations = aggregations;
    }
}