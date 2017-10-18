package com.seeworld.api.domain.valueobject;

public class GetInsightsServiceResponse implements IResponseMessage<Boolean> {

    private final ErrorDetails errorDetails;
    private Boolean isSuccessful;

    public GetInsightsServiceResponse(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
        this.errorDetails = null;
    }

    public GetInsightsServiceResponse(ErrorDetails errorDetails) {
        isSuccessful = false;
        this.errorDetails = errorDetails;
    }

    @Override
    public Boolean getValue() {
        return isSuccessful;
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public boolean isSuccessful() {
        return errorDetails == null;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}