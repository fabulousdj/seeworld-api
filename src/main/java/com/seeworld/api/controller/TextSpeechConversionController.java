package com.seeworld.api.controller;

import com.seeworld.api.domain.mapper.ResponseEntityMapper;
import com.seeworld.api.domain.service.ITextSpeechConversionService;
import com.seeworld.api.domain.valueobject.IResponseMessage;
import com.seeworld.api.domain.valueobject.SpeechToTextResponse;
import com.seeworld.api.domain.valueobject.TextToSpeechResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jidai on 9/10/17.
 * Text-Speech Conversion API Controller
 */
@RestController
@RequestMapping("seeworld/api/v1/text-speech-conversion")
public class TextSpeechConversionController {

    private final ITextSpeechConversionService textSpeechConversionService;
    private final ResponseEntityMapper responseEntityMapper;

    @Autowired
    public TextSpeechConversionController(ITextSpeechConversionService textSpeechConversionService,
                                          ResponseEntityMapper responseEntityMapper) {
        this.textSpeechConversionService = textSpeechConversionService;
        this.responseEntityMapper = responseEntityMapper;
    }

    @RequestMapping("/speech-to-text")
    public ResponseEntity<? extends IResponseMessage> getSpeechToTextResult() {
        SpeechToTextResponse response = textSpeechConversionService.getSpeechToTextResult();
        return responseEntityMapper.mapWithRequestId(response);
    }

    @RequestMapping(value = "/text-to-speech", method = RequestMethod.GET)
    public void getTextToSpeechResult(
            HttpServletResponse response,
            @RequestParam("text") final String text) throws IOException {
        TextToSpeechResponse textToSpeechResponse = textSpeechConversionService.getTextToSpeechResult(text);
        String speechFileName = textToSpeechResponse.getSpeechFileName();
        response.addHeader("Content-disposition", "attachment;filename=" + speechFileName);
        response.setContentType("audio/wav");

        // Copy the stream to the response's output stream.
        IOUtils.copy(textToSpeechResponse.getInputStream(), response.getOutputStream());
        response.flushBuffer();
    }
}
