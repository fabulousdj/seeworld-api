package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

public class ConversationResponse {

    @JsonProperty("system")
    private ConversationSystemContext systemContext;
    @JsonProperty("intent")
    private String intent;
    @JsonProperty("response")
    private List<String> response;

    public ConversationResponse(ConversationSystemContext systemContext, String intent, List<String> response) {
        this.systemContext = systemContext;
        this.intent = intent;
        this.response = response;
    }

    public ConversationSystemContext getSystemContext() {
        return systemContext;
    }

    public void setSystemContext(ConversationSystemContext systemContext) {
        this.systemContext = systemContext;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }
}
