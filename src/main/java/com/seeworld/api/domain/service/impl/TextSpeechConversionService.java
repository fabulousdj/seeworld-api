package com.seeworld.api.domain.service.impl;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import com.seeworld.api.domain.service.ITextSpeechConversionService;
import com.seeworld.api.domain.valueobject.SpeechToTextResponse;
import com.seeworld.api.domain.valueobject.TextToSpeechResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class TextSpeechConversionService implements ITextSpeechConversionService {

    private static final String DATE_FORMAT = "yyyy-MM-dd_HH:mm:ss";
    private static final String TIME_ZONE_UTC = "UTC";
    private static final String WAV_SUFFIX = ".wav";

    @Value("${watsonCloudService.speechToText.username}")
    private String speechToTextServiceUsername;
    @Value("${watsonCloudService.speechToText.password}")
    private String speechToTextServicePassword;

    @Value("${watsonCloudService.textToSpeech.username}")
    private String textToSpeechServiceUsername;
    @Value("${watsonCloudService.textToSpeech.password}")
    private String textToSpeechServicePassword;

    @Override
    public SpeechToTextResponse getSpeechToTextResult() {
        SpeechToText service = new SpeechToText(
                speechToTextServiceUsername,
                speechToTextServicePassword);

        RecognizeOptions options = new RecognizeOptions.Builder().contentType("audio/wav").build();

        SpeechResults results = service.recognize(new File("hello_world.wav"), options).execute();
        System.out.println(results);

        Transcript resultTranscript = results.getResults().get(0);
        SpeechAlternative speechAlternative = resultTranscript.getAlternatives().get(0);
        return new SpeechToTextResponse(speechAlternative.getTranscript());
    }

    @Override
    public TextToSpeechResponse getTextToSpeechResult(String text) throws IOException {
        TextToSpeech service = new TextToSpeech(
                textToSpeechServiceUsername,
                textToSpeechServicePassword);

        InputStream stream = service.synthesize(text, Voice.EN_ALLISON, AudioFormat.WAV).execute();
        return new TextToSpeechResponse(WaveUtils.reWriteWaveHeader(stream),
                getTimestamp() + WAV_SUFFIX);
    }

    private String getTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_UTC));
        final String timestamp = dateFormat.format(new Date());
        return timestamp;
    }
}
