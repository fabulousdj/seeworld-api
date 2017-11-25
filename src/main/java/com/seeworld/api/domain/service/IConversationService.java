package com.seeworld.api.domain.service;

import com.seeworld.api.domain.valueobject.ConversationServiceResponse;
import com.seeworld.api.domain.valueobject.ConversationContext;

public interface IConversationService {
    ConversationServiceResponse sendMessage(ConversationContext context);
}
