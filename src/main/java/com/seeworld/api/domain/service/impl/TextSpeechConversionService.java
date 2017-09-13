package com.seeworld.api.domain.service.impl;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import com.seeworld.api.domain.service.ITextSpeechConversionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class TextSpeechConversionService implements ITextSpeechConversionService {

    @Value("${watsonCloudService.textToSpeech.username}")
    private String username;
    @Value("${watsonCloudService.textToSpeech.password}")
    private String password;

    @Override
    public String getSpeechToTextResult() {
        return null;
    }

    @Override
    public String getTextToSpeechResult() {
        TextToSpeech service = new TextToSpeech(username, password);

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
