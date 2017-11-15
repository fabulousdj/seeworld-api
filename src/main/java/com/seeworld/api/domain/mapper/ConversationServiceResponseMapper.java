package com.seeworld.api.domain.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.NestableAggregationResult;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.QueryResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.Term;
import com.seeworld.api.domain.valueobject.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConversationServiceResponseMapper {

    public ConversationServiceResponse mapConversationServiceResponse(final MessageResponse messageResponse) {
        ConversationResponse conversationResponse = buildConversationResponse(messageResponse);
        return new ConversationServiceResponse(conversationResponse);
    }

    private ConversationResponse buildConversationResponse(MessageResponse messageResponse) {
        ObjectMapper systemContextMapper = new ObjectMapper();
        ConversationSystemContext systemContext = systemContextMapper.convertValue(
                messageResponse.getContext().get("system"), ConversationSystemContext.class);
        String intent = messageResponse.getIntents().get(0).getIntent();
        List<String> responses = messageResponse.getText();
        return new ConversationResponse(systemContext, intent, responses);
    }

}
