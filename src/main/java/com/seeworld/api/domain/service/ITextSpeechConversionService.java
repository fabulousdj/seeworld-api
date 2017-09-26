package com.seeworld.api.domain.service;

import com.seeworld.api.domain.valueobject.SpeechToTextResponse;
import com.seeworld.api.domain.valueobject.TextToSpeechResponse;

import java.io.IOException;

public interface ITextSpeechConversionService {
    SpeechToTextResponse getSpeechToTextResult();
    TextToSpeechResponse getTextToSpeechResult(String text) throws IOException;
}
