package com.seeworld.api.domain.mapper;

import com.seeworld.api.diagnostics.SystemEvent;
import com.seeworld.api.domain.valueobject.IResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Created by jidai on 9/19/17.
 * Response Entity mapper to map the response
 */
@Component
public class ResponseEntityMapper {

    public <T extends IResponseMessage> ResponseEntity<T> mapWithRequestId(T message) {
        if (!message.isSuccessful()) {
            if (SystemEvent.isInputValidationError(message.getErrorDetails().getErrorCode())) {
                return ResponseEntity.badRequest().body(message);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
            }
        } else {
            return ResponseEntity.ok(message);
        }
    }
}
