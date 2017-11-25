package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class ConversationContext {
    @JsonProperty("input")
    private String input;
    @JsonProperty("previous")
    private String previousDestination;
    @JsonProperty("prev_dest")
    private boolean hasPreviousDestination;
    @JsonProperty("valid_dest")
    private boolean isDestinationValid;
    @JsonProperty("valid_review")
    private boolean isReviewValid;
    @JsonProperty("verifyAdd")
    private boolean shouldVerifyAddress;
    @JsonProperty("node_name")
    private String nodeName;
    @JsonProperty("conversation_id")
    private String conversationId;
    @JsonProperty("system")
    private ConversationSystemContext system;

    public String getPreviousDestination() {
        return previousDestination;
    }

    public void setPreviousDestination(String previousDestination) {
        this.previousDestination = previousDestination;
    }

    public boolean isHasPreviousDestination() {
        return hasPreviousDestination;
    }

    public void setHasPreviousDestination(boolean hasPreviousDestination) {
        this.hasPreviousDestination = hasPreviousDestination;
    }

    public boolean isDestinationValid() {
        return isDestinationValid;
    }

    public void setDestinationValid(boolean destinationValid) {
        isDestinationValid = destinationValid;
    }

    public boolean isReviewValid() {
        return isReviewValid;
    }

    public void setReviewValid(boolean reviewValid) {
        isReviewValid = reviewValid;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public ConversationSystemContext getSystem() {
        return system;
    }

    public void setSystem(ConversationSystemContext system) {
        this.system = system;
    }

    public boolean isShouldVerifyAddress() {
        return shouldVerifyAddress;
    }

    public void setShouldVerifyAddress(boolean shouldVerifyAddress) {
        this.shouldVerifyAddress = shouldVerifyAddress;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
