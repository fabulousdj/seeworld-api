package com.seeworld.api.dictionary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppStateClassifierIdDictionary {
    private static Map<String, List<String>> CLASSIFIER_ID_DICTIONARY = null;

    @Value("${appContext.transportationMethodSelection}")
    private String appTransportationMethodSelectionState;
    @Value("${watsonCloudService.naturalLanguageClassifier.id.transportationMethodClassifier}")
    private String transportationMethodClassifierId;

    @Value("${appContext.booleanClassification}")
    private String appBooleanClassificationState;
    @Value("${watsonCloudService.naturalLanguageClassifier.id.booleanClassifier}")
    private String booleanClassifierId;

    @Value("${appContext.waitingForDestination}")
    private String appWaitingForDestinationState;
    @Value("${watsonCloudService.naturalLanguageClassifier.id.destinationChoiceClassifier}")
    private String destinationChoiceClassifierId;

    @Value("${appContext.waitingForUserReview}")
    private String appWaitingForUserReviewState;
    @Value("${watsonCloudService.naturalLanguageClassifier.id.postUserReviewClassifier}")
    private String postUserReviewClassifierId;

    public boolean containsAppState(String appState) {
        if (CLASSIFIER_ID_DICTIONARY == null) {
            CLASSIFIER_ID_DICTIONARY = buildClassifierIdDictionary();
        }
        return CLASSIFIER_ID_DICTIONARY.containsKey(appState);
    }

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
        dictionary.put(appBooleanClassificationState,
                new LinkedList<>(Arrays.asList(booleanClassifierId)));
        dictionary.put(appWaitingForDestinationState,
                new LinkedList<>(Arrays.asList(destinationChoiceClassifierId)));
        dictionary.put(appWaitingForUserReviewState,
                new LinkedList<>(Arrays.asList(postUserReviewClassifierId)));
        return dictionary;
    }
}
