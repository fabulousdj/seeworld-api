package com.seeworld.api.domain.valueobject;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class ConversationServiceResponse implements IResponseMessage<ConversationResponse>  {

    private final ErrorDetails errorDetails;
    private ConversationResponse response;

    public ConversationServiceResponse(ConversationResponse response) {
        this.response = response;
        this.errorDetails = null;
    }

    public ConversationServiceResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public void setResponse(ConversationResponse response) {
        this.response = response;
    }

    @Override
    public ConversationResponse getValue() {
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
