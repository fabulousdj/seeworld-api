package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConversationContext {
    @JsonProperty("ets")
    private String eta;
    @JsonProperty("previous")
    private String previousDestination;
    @JsonProperty("prev_dest")
    private boolean hasPreviousDestination;
    @JsonProperty("valid_dest")
    private boolean isDestinationValid;
    @JsonProperty("valid_review")
    private boolean isReviewValid;
    @JsonProperty("weather")
    private String weatherInfo;
    @JsonProperty("time")
    private String timeInfo;
    @JsonProperty("conversation_id")
    private String conversationId;
    @JsonProperty("system")
    private ConversationSystemContext system;

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

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

    public String getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(String weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public String getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(String timeInfo) {
        this.timeInfo = timeInfo;
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
}
