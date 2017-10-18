package com.seeworld.api.domain.valueobject;

import com.ibm.watson.developer_cloud.discovery.v1.model.document.UpdateDocumentResponse;

public class PostUserReviewServiceResponse implements IResponseMessage<UpdateDocumentResponse>  {

    private final ErrorDetails errorDetails;
    private UpdateDocumentResponse response;

    public PostUserReviewServiceResponse(UpdateDocumentResponse response) {
        this.response = response;
        this.errorDetails = null;
    }

    public PostUserReviewServiceResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public void setResponse(UpdateDocumentResponse response) {
        this.response = response;
    }

    @Override
    public UpdateDocumentResponse getValue() {
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
