package com.seeworld.api.controller;

import com.seeworld.api.domain.service.ITextSpeechConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jidai on 9/10/17.
 * Sample API Controller
 */
@RestController
@RequestMapping("api")
public class TextSpeechConversionController {

    private final ITextSpeechConversionService textSpeechConversionService;

    @Autowired
    public TextSpeechConversionController(ITextSpeechConversionService textSpeechConversionService) {
        this.textSpeechConversionService = textSpeechConversionService;
    }

    @RequestMapping("/speech-to-text")
    public List<Integer> getSpeechToTextResult() {
        List<Integer> sampleIntegers = new ArrayList<>();
        sampleIntegers.add(1);
        sampleIntegers.add(0);
        sampleIntegers.add(0);
        sampleIntegers.add(8);
        sampleIntegers.add(6);
        return sampleIntegers;
    }

    @RequestMapping("/text-to-speech")
    public String getTextToSpeechResult() {
        return textSpeechConversionService.getTextToSpeechResult();
    }
}
