package com.seeworld.api.domain.service.impl;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.UpdateDocumentRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.UpdateDocumentResponse;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.seeworld.api.dictionary.DiscoveryCollectionIdDictionary;
import com.seeworld.api.domain.service.IUserReviewsService;
import com.seeworld.api.domain.valueobject.GetInsightsServiceResponse;
import com.seeworld.api.domain.valueobject.UserReview;
import com.seeworld.api.domain.valueobject.PostUserReviewServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

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

    @Autowired
    private DiscoveryCollectionIdDictionary collectionIdDictionary;

    @Override
    public GetInsightsServiceResponse getInsights(String destination) {
        return null;
    }

    @Override
    public PostUserReviewServiceResponse postReview(UserReview review) {
        Discovery discovery = new Discovery(discoveryServiceVersion);
        discovery.setEndPoint(discoveryServiceEndPoint);
        discovery.setUsernameAndPassword(discoveryServiceUsername, discoveryServicePassword);
        String environmentId = discoveryServiceEnvironmentId;
        String collectionName = review.getLocation().buildCollectionName();
        String collectionId = collectionIdDictionary.getCollectionIdByName(collectionName);
        String documentId = UUID.randomUUID().toString();
        Gson gson = new Gson();
        String updatedDocumentJson = gson.toJson(review, UserReview.class);
        InputStream updatedDocumentStream = new ByteArrayInputStream(updatedDocumentJson.getBytes());

        UpdateDocumentRequest.Builder updateBuilder = new UpdateDocumentRequest.Builder(environmentId, collectionId, documentId);
        updateBuilder.inputStream(updatedDocumentStream, HttpMediaType.APPLICATION_JSON);
        UpdateDocumentResponse updateResponse = discovery.updateDocument(updateBuilder.build()).execute();
        return new PostUserReviewServiceResponse(updateResponse);
    }
}
