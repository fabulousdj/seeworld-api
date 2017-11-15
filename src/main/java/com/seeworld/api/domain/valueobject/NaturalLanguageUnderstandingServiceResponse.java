package com.seeworld.api.domain.valueobject;

public class NaturalLanguageUnderstandingServiceResponse implements IResponseMessage<String>  {

    private final ErrorDetails errorDetails;
    private String destination;

    public NaturalLanguageUnderstandingServiceResponse(String destination) {
        this.destination = destination;
        this.errorDetails = null;
    }

    public NaturalLanguageUnderstandingServiceResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String getValue() {
        return destination;
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
