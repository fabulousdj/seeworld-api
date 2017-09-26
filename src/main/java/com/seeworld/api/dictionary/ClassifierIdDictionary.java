package com.seeworld.api.dictionary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ClassifierIdDictionary {
    private static Map<String, List<String>> CLASSIFIER_ID_DICTIONARY = null;

    @Value("${appContext.transportationMethodSelection}")
    private String appTransportationMethodSelectionState;
    @Value("${watsonCloudService.naturalLanguageClassifier.id.transportationMethodClassifier}")
    private String transportationMethodClassifierId;

    public List<String> getClassifierIdByAppState(String state) {
        if (CLASSIFIER_ID_DICTIONARY == null) {
            CLASSIFIER_ID_DICTIONARY = buildClassifierIdDictionary();
        }
        return CLASSIFIER_ID_DICTIONARY.get(state);
    }

    private Map<String, List<String>> buildClassifierIdDictionary() {
        Map<String, List<String>> dictionary = new HashMap<>();

        dictionary.put(appTransportationMethodSelectionState,
                new LinkedList<>(Arrays.asList(transportationMethodClassifierId)));
        return dictionary;
    }
}
