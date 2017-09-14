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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Component
public class TextSpeechConversionService implements ITextSpeechConversionService {

    @Value("${watsonCloudService.speechToText.username}")
    private String speechToTextServiceUsername;
    @Value("${watsonCloudService.speechToText.password}")
    private String speechToTextServicePassword;

    @Value("${watsonCloudService.textToSpeech.username}")
    private String textToSpeechServiceUsername;
    @Value("${watsonCloudService.textToSpeech.password}")
    private String textToSpeechServicePassword;

    @Override
    public String getSpeechToTextResult() {
        SpeechToText service = new SpeechToText(
                speechToTextServiceUsername,
                speechToTextServicePassword);

        RecognizeOptions options = new RecognizeOptions.Builder().contentType("audio/wav").build();

        SpeechResults results = service.recognize(new File("hello_world.wav"), options).execute();
        System.out.println(results);

        Transcript resultTranscript = results.getResults().get(0);
        SpeechAlternative speechAlternative = resultTranscript.getAlternatives().get(0);
        return speechAlternative.getTranscript();
    }

    @Override
    public String getTextToSpeechResult() {
        TextToSpeech service = new TextToSpeech(
                textToSpeechServiceUsername,
                textToSpeechServicePassword);

        try {
            String text = "Hello world";
            InputStream stream = service.synthesize(text, Voice.EN_ALLISON,
                    AudioFormat.WAV).execute();
            InputStream in = WaveUtils.reWriteWaveHeader(stream);
            OutputStream out = new FileOutputStream("hello_world.wav");
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            stream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
