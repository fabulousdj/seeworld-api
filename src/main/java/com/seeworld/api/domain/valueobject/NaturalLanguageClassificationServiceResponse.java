package com.seeworld.api.domain.valueobject;

public class NaturalLanguageClassificationServiceResponse implements IResponseMessage<String>  {

    private final ErrorDetails errorDetails;
    private String classification;

    public NaturalLanguageClassificationServiceResponse(String classification) {
        this.classification = classification;
        this.errorDetails = null;
    }

    public NaturalLanguageClassificationServiceResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String getValue() {
        return classification;
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
