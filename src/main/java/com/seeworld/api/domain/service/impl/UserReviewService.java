package com.seeworld.api.domain.service.impl;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.CreateDocumentRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.CreateDocumentResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.QueryRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.QueryResponse;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.seeworld.api.dictionary.DiscoveryCollectionIdDictionary;
import com.seeworld.api.domain.mapper.GetInsightsServiceResponseMapper;
import com.seeworld.api.domain.service.IUserReviewsService;
import com.seeworld.api.domain.valueobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class UserReviewService implements IUserReviewsService {

    @Value("${watsonCloudService.discovery.username}")
    private String discoveryServiceUsername;
    @Value("${watsonCloudService.discovery.password}")
    private String discoveryServicePassword;
    @Value("${watsonCloudService.discovery.version}")
    private String discoveryServiceVersion;
    @Value("${watsonCloudService.discovery.endPoint}")
    private String discoveryServiceEndPoint;
    @Value("${watsonCloudService.discovery.environmentId}")
    private String discoveryServiceEnvironmentId;
    @Value("${watsonCloudService.discovery.collections.columbus-OH-US.name}")
    private String collectionName;

    @Autowired
    private GetInsightsServiceResponseMapper getInsightsServiceResponseMapper;
    @Autowired
    private DiscoveryCollectionIdDictionary collectionIdDictionary;

    @Override
    public GetInsightsServiceResponse getInsights(PlaceInfo destination) {
        Discovery discovery = new Discovery(discoveryServiceVersion);
        discovery.setEndPoint(discoveryServiceEndPoint);
        discovery.setUsernameAndPassword(discoveryServiceUsername, discoveryServicePassword);
        String environmentId = discoveryServiceEnvironmentId;
        String collectionId = collectionIdDictionary.getCollectionIdByName(collectionName);

        QueryRequest.Builder queryBuilder = new QueryRequest.Builder(environmentId, collectionId);
        queryBuilder.query("location.name:\"" + destination.getName() + "\",location.address:\"" + destination.getAddress() + "\"");
        queryBuilder.aggregation("term(enriched_review.sentiment.document.label)");
        QueryResponse queryResponse = discovery.query(queryBuilder.build()).execute();
        return getInsightsServiceResponseMapper.mapGetInsightsServiceResponse(queryResponse);
    }

    @Override
    public PostUserReviewServiceResponse postReview(UserReview review) {
        Discovery discovery = new Discovery(discoveryServiceVersion);
        discovery.setEndPoint(discoveryServiceEndPoint);
        discovery.setUsernameAndPassword(discoveryServiceUsername, discoveryServicePassword);
        String environmentId = discoveryServiceEnvironmentId;
        String collectionId = collectionIdDictionary.getCollectionIdByName(collectionName);
        Gson gson = new Gson();
        String documentJson = gson.toJson(review, UserReview.class);
        InputStream documentStream = new ByteArrayInputStream(documentJson.getBytes());

        CreateDocumentRequest.Builder builder = new CreateDocumentRequest.Builder(environmentId, collectionId);
        builder.file(documentStream, HttpMediaType.APPLICATION_JSON);
        CreateDocumentResponse createDocumentResponse = discovery.createDocument(builder.build()).execute();
        return new PostUserReviewServiceResponse(createDocumentResponse);
    }
}
