package com.seeworld.api.controller;

import com.seeworld.api.domain.mapper.ResponseEntityMapper;
import com.seeworld.api.domain.service.INaturalLanguageProcessingService;
import com.seeworld.api.domain.valueobject.IResponseMessage;
import com.seeworld.api.domain.valueobject.NaturalLanguageClassificationServiceResponse;
import com.seeworld.api.domain.valueobject.NaturalLanguageUnderstandingServiceResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @CrossOrigin
    @RequestMapping(value = "/classify", method = RequestMethod.GET, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = NaturalLanguageClassificationServiceResponse.class),
            @ApiResponse(code = 500, message = "Error", response = NaturalLanguageClassificationServiceResponse.class)})
    public ResponseEntity<? extends IResponseMessage> classify(
            @RequestParam("input") final String input,
            @RequestParam("state") final String appState) {
        NaturalLanguageClassificationServiceResponse response
                = naturalLanguageProcessingService.classify(input, appState);
        return responseEntityMapper.mapWithRequestId(response);
    }
}
