package com.seeworld.api.domain.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
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
        ObjectMapper objectMapper = new ObjectMapper();
        ConversationSystemContext systemContext = objectMapper.convertValue(
                messageResponse.getContext().get("system"), ConversationSystemContext.class);
        String nodeName = (String) messageResponse.getContext().get("node_name");
        String input = (String) messageResponse.getContext().get("input");
        List<String> responses = messageResponse.getText();
        return new ConversationResponse(systemContext, nodeName, input, responses);
    }

}
