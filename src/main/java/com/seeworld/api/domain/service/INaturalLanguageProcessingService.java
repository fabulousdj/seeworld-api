package com.seeworld.api.domain.service;

import com.seeworld.api.domain.valueobject.NaturalLanguageClassificationServiceResponse;
import com.seeworld.api.domain.valueobject.NaturalLanguageUnderstandingServiceResponse;

public interface INaturalLanguageProcessingService {
    NaturalLanguageClassificationServiceResponse classify(String input, String appState);
    NaturalLanguageUnderstandingServiceResponse understandDestination(String input);
}
