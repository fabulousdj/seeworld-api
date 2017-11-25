package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConversationResponse {

    @JsonProperty("system")
    private ConversationSystemContext systemContext;
    @JsonProperty("nodeName")
    private String nodeName;
    @JsonProperty("response")
    private List<String> response;

    public ConversationResponse(ConversationSystemContext systemContext, String nodeName, List<String> response) {
        this.systemContext = systemContext;
        this.nodeName = nodeName;
        this.response = response;
    }

    public ConversationSystemContext getSystemContext() {
        return systemContext;
    }

    public void setSystemContext(ConversationSystemContext systemContext) {
        this.systemContext = systemContext;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }
}
