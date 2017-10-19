package com.seeworld.api.domain.mapper;

import com.ibm.watson.developer_cloud.discovery.v1.model.query.NestableAggregationResult;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.QueryResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.Term;
import com.seeworld.api.domain.valueobject.GetInsightsServiceResponse;
import com.seeworld.api.domain.valueobject.UserReviewInsights;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetInsightsServiceResponseMapper {

    private static String POSITIVE_LABEL = "positive";
    private static String NEGATIVE_LABEL = "negative";

    public GetInsightsServiceResponse mapGetInsightsServiceResponse(final QueryResponse queryResponse) {
        UserReviewInsights insights = buildUserReviewInsights(queryResponse);
        return new GetInsightsServiceResponse(insights);
    }

    private UserReviewInsights buildUserReviewInsights(QueryResponse queryResponse) {

        Term aggregationTerm = (Term) queryResponse.getAggregations().get(0);
        List<NestableAggregationResult> results = aggregationTerm.getResults();

        long count = 0;
        long positiveCount = 0;
        long negativeCount = 0;
        long neutralCount = 0;

        for (NestableAggregationResult result : results) {
            long matchingResultsCount = result.getMatchingResults();
            count += matchingResultsCount;
            if (result.getKey().equals(POSITIVE_LABEL)) {
                positiveCount += matchingResultsCount;
            } else if (result.getKey().equals(NEGATIVE_LABEL)) {
                negativeCount += result.getMatchingResults();
            } else {
                neutralCount += result.getMatchingResults();
            }
        }
        return new UserReviewInsights(count, positiveCount, negativeCount, neutralCount);
    }

}
