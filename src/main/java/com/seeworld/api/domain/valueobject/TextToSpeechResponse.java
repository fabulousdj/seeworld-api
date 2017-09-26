package com.seeworld.api.domain.valueobject;

import java.io.InputStream;

public class TextToSpeechResponse {
    private InputStream in;
    private String speechFileName;

    public TextToSpeechResponse(InputStream in, String speechFileName) {
        this.in = in;
        this.speechFileName = speechFileName;
    }

    public InputStream getInputStream() {
        return in;
    }

    public String getSpeechFileName() {
        return speechFileName;
    }
}
