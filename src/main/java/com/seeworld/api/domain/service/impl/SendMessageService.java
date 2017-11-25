package com.seeworld.api.domain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.seeworld.api.domain.mapper.ConversationServiceResponseMapper;
import com.seeworld.api.domain.service.IConversationService;
import com.seeworld.api.domain.valueobject.ConversationServiceResponse;
import com.seeworld.api.domain.valueobject.ConversationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SendMessageService implements IConversationService {

    @Value("${watsonCloudService.conversation.username}")
    private String conversationServiceUsername;
    @Value("${watsonCloudService.conversation.password}")
    private String conversationServicePassword;
    @Value("${watsonCloudService.conversation.workspaceId}")
    private String workspaceId;

    @Autowired
    ConversationServiceResponseMapper conversationServiceResponseMapper;

    @Override
    public ConversationServiceResponse sendMessage(ConversationContext context) {
        ConversationService conversationService = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
        conversationService.setUsernameAndPassword(conversationServiceUsername, conversationServicePassword);
        ObjectMapper contextMapper = new ObjectMapper();
        Map<String, Object> contextMap = contextMapper.convertValue(context, Map.class);

        MessageRequest newMessage = new MessageRequest.Builder()
                .inputText(context.getInput()).context(contextMap).build();

        MessageResponse response = conversationService.message(workspaceId, newMessage).execute();
        return conversationServiceResponseMapper.mapConversationServiceResponse(response);
    }
}
