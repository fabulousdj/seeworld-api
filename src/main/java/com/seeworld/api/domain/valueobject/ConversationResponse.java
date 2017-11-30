package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ConversationResponse {

    @JsonProperty("system")
    private Map systemContext;
    @JsonProperty("nodeName")
    private String nodeName;
    @JsonProperty("input")
    private String input;
    @JsonProperty("response")
    private List<String> response;

    public ConversationResponse(Map systemContext, String nodeName, String input, List<String> response) {
        this.systemContext = systemContext;
        this.nodeName = nodeName;
        this.input = input;
        this.response = response;
    }

    public Map getSystemContext() {
        return systemContext;
    }

    public void setSystemContext(Map systemContext) {
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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
