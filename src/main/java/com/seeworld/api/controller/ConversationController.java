package com.seeworld.api.controller;

import com.seeworld.api.domain.mapper.ResponseEntityMapper;
import com.seeworld.api.domain.service.IConversationService;
import com.seeworld.api.domain.valueobject.ConversationServiceResponse;
import com.seeworld.api.domain.valueobject.ConversationContext;
import com.seeworld.api.domain.valueobject.IResponseMessage;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by jidai on 11/15/17.
 * Conversation API Controller
 */
@RestController
@RequestMapping("seeworld/api/v1/conversation")
public class ConversationController {

    private final IConversationService conversationService;
    private final ResponseEntityMapper responseEntityMapper;

    @Autowired
    public ConversationController(IConversationService conversationService,
                                               ResponseEntityMapper responseEntityMapper) {
        this.conversationService = conversationService;
        this.responseEntityMapper = responseEntityMapper;
    }

    @CrossOrigin
    @RequestMapping(value = "/send-message", method = RequestMethod.POST, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ConversationServiceResponse.class),
            @ApiResponse(code = 500, message = "Error", response = ConversationServiceResponse.class)})
    public ResponseEntity<? extends IResponseMessage> sendMessage(
            @RequestParam("input") final String input,
            @RequestBody final ConversationContext context
            ) {
        ConversationServiceResponse response = conversationService.sendMessage(input, context);
        return responseEntityMapper.mapWithRequestId(response);
    }
}
