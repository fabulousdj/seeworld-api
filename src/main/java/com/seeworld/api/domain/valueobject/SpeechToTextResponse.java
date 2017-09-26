package com.seeworld.api.domain.valueobject;

public class SpeechToTextResponse implements IResponseMessage<String>  {

    private final ErrorDetails errorDetails;
    private String textResult;

    public SpeechToTextResponse(String textResult) {
        this.textResult = textResult;
        this.errorDetails = null;
    }

    public SpeechToTextResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }

    @Override
    public String getValue() {
        return textResult;
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