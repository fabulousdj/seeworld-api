package com.seeworld.api.domain.service;

import com.seeworld.api.domain.valueobject.AppContext;
import com.seeworld.api.domain.valueobject.NaturalLanguageClassificationServiceResponse;

public interface INaturalLanguageProcessingService {
    NaturalLanguageClassificationServiceResponse classify(String input, AppContext appContext);
}
