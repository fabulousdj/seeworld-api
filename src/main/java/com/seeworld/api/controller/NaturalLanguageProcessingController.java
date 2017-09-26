package com.seeworld.api.controller;

import com.seeworld.api.domain.mapper.ResponseEntityMapper;
import com.seeworld.api.domain.service.INaturalLanguageProcessingService;
import com.seeworld.api.domain.valueobject.AppContext;
import com.seeworld.api.domain.valueobject.IResponseMessage;
import com.seeworld.api.domain.valueobject.NaturalLanguageClassificationServiceResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jidai on 9/19/17.
 * Natural Language Processing Controller
 */

@RestController
@RequestMapping("seeworld/api/v1/natural-language-processing")
public class NaturalLanguageProcessingController {
    private final INaturalLanguageProcessingService naturalLanguageProcessingService;
    private final ResponseEntityMapper responseEntityMapper;

    @Autowired
    public NaturalLanguageProcessingController(INaturalLanguageProcessingService naturalLanguageProcessingService,
                                               ResponseEntityMapper responseEntityMapper) {
        this.naturalLanguageProcessingService = naturalLanguageProcessingService;
        this.responseEntityMapper = responseEntityMapper;
    }

    @ApiOperation(value = "Classify User Input", nickname = "Classify User Input")
    @RequestMapping(value = "/classify", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<? extends IResponseMessage> classify(
            @RequestParam("input") final String input,
            @RequestBody(required = false) final AppContext appContext) {
        NaturalLanguageClassificationServiceResponse response
                = naturalLanguageProcessingService.classify(input, appContext);
        return responseEntityMapper.mapWithRequestId(response);
    }
}