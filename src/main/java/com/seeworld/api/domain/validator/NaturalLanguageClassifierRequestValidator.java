package com.seeworld.api.domain.validator;

import com.seeworld.api.dictionary.AppStateClassifierIdDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NaturalLanguageClassifierRequestValidator {

    @Autowired
    private AppStateClassifierIdDictionary appStateClassifierIdDictionary;

    public boolean validateRequest(final String appState) {
        return appStateClassifierIdDictionary.containsAppState(appState);
    }
}
