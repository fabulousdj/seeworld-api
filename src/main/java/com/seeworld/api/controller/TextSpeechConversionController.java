package com.seeworld.api.controller;

import com.seeworld.api.domain.service.ITextSpeechConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jidai on 9/10/17.
 * Text-Speech Conversion API Controller
 */
@RestController
@RequestMapping("seeworld/api")
public class TextSpeechConversionController {

    private final ITextSpeechConversionService textSpeechConversionService;

    @Autowired
    public TextSpeechConversionController(ITextSpeechConversionService textSpeechConversionService) {
        this.textSpeechConversionService = textSpeechConversionService;
    }

    @RequestMapping("/speech-to-text")
    public String getSpeechToTextResult() {
        return textSpeechConversionService.getSpeechToTextResult();
    }

    @RequestMapping("/text-to-speech")
    public String getTextToSpeechResult() {
        return textSpeechConversionService.getTextToSpeechResult();
    }
}
