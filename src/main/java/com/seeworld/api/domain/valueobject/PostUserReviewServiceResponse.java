package com.seeworld.api.domain.valueobject;

import com.ibm.watson.developer_cloud.discovery.v1.model.document.CreateDocumentResponse;

public class PostUserReviewServiceResponse implements IResponseMessage<CreateDocumentResponse>  {

    private final ErrorDetails errorDetails;
    private CreateDocumentResponse response;

    public PostUserReviewServiceResponse(CreateDocumentResponse response) {
        this.response = response;
        this.errorDetails = null;
    }

    public PostUserReviewServiceResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public void setResponse(CreateDocumentResponse response) {
        this.response = response;
    }

    @Override
    public CreateDocumentResponse getValue() {
        return response;
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public boolean isSuccessful() {
        return errorDetails == null;
    }
}
