package com.seeworld.api.domain.service.helper;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.seeworld.api.dictionary.ClassifierIdDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NaturalLanguageClassifierHelper {
    private static String CLASSIFIED_RESULT_NORMAL = "normal";

    @Autowired
    private ClassifierIdDictionary classifierIdDictionary;

    public String getClassifiedResultByAppState(NaturalLanguageClassifier service, String appState, String input) {
        List<String> classifierList = classifierIdDictionary.getClassifierIdByAppState(appState);
        String result = CLASSIFIED_RESULT_NORMAL;
        int index = 0;
        while (result.equals(CLASSIFIED_RESULT_NORMAL)) {
            result = getResultByClassifier(service, classifierList.get(index), input);
            index++;
        }
        return result;
    }

    public String getResultByClassifier(NaturalLanguageClassifier service, String classifierId, String input) {
        Classification classification = service.classify(classifierId, input).execute();
        return classification.getTopClass();
    }
}
