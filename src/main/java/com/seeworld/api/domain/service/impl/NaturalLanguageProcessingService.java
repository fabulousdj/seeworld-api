package com.seeworld.api.domain.service.impl;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.seeworld.api.domain.service.INaturalLanguageProcessingService;
import com.seeworld.api.domain.service.helper.NaturalLanguageClassifierHelper;
import com.seeworld.api.domain.valueobject.AppContext;
import com.seeworld.api.domain.valueobject.NaturalLanguageClassificationServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class NaturalLanguageProcessingService implements INaturalLanguageProcessingService {

    private static String CLASSIFIED_RESULT_NORMAL = "normal";

    @Value("${watsonCloudService.naturalLanguageClassifier.username}")
    private String naturalLanguageClassifierServiceUsername;
    @Value("${watsonCloudService.naturalLanguageClassifier.password}")
    private String naturalLanguageClassifierServicePassword;
    @Value("${watsonCloudService.naturalLanguageClassifier.id.globalInstructionClassifier}")
    private String globalInstructionClassifierId;

    @Autowired
    private NaturalLanguageClassifierHelper naturalLanguageClassifierHelper;

    @Override
    public NaturalLanguageClassificationServiceResponse classify(String input, AppContext appContext) {
        NaturalLanguageClassifier service = new NaturalLanguageClassifier(
                naturalLanguageClassifierServiceUsername,
                naturalLanguageClassifierServicePassword);

        //String appState = appContext.getState();
        String appState = "sw_trans_method_selection";
        String globalInstrClassifierResult = naturalLanguageClassifierHelper.getResultByClassifier(
                service, globalInstructionClassifierId, input);

        String result = globalInstrClassifierResult;
        if (globalInstrClassifierResult.equals(CLASSIFIED_RESULT_NORMAL)) {
            result = naturalLanguageClassifierHelper.getClassifiedResultByAppState(service, appState, input);
        }

        return new NaturalLanguageClassificationServiceResponse(result);
    }
}
