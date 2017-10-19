package com.seeworld.api.domain.service.impl;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.seeworld.api.diagnostics.SystemEvent;
import com.seeworld.api.domain.service.INaturalLanguageProcessingService;
import com.seeworld.api.domain.service.helper.NaturalLanguageClassifierHelper;
import com.seeworld.api.domain.validator.NaturalLanguageClassifierRequestValidator;
import com.seeworld.api.domain.valueobject.ErrorDetails;
import com.seeworld.api.domain.valueobject.ISystemEvent;
import com.seeworld.api.domain.valueobject.NaturalLanguageClassificationServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class NaturalLanguageProcessingService implements INaturalLanguageProcessingService {

    private static String CLASSIFIED_RESULT_TRASH = "trash";

    @Value("${watsonCloudService.naturalLanguageClassifier.username}")
    private String naturalLanguageClassifierServiceUsername;
    @Value("${watsonCloudService.naturalLanguageClassifier.password}")
    private String naturalLanguageClassifierServicePassword;
    @Value("${watsonCloudService.naturalLanguageClassifier.id.globalInstructionClassifier}")
    private String globalInstructionClassifierId;

    @Autowired
    private NaturalLanguageClassifierHelper naturalLanguageClassifierHelper;
    @Autowired
    private NaturalLanguageClassifierRequestValidator requestValidator;

    @Override
    public NaturalLanguageClassificationServiceResponse classify(String input, String appState) {
        ISystemEvent systemEvent = null;
        final boolean isValidRequest = requestValidator.validateRequest(appState);

        if (!isValidRequest) {
            systemEvent = SystemEvent.EPC_SEARCH_SERVICE_INVALID_REQUEST;
            return new NaturalLanguageClassificationServiceResponse(
                    new ErrorDetails(systemEvent.getId(), systemEvent.getDescription()));
        }

        NaturalLanguageClassifier service = new NaturalLanguageClassifier(
                naturalLanguageClassifierServiceUsername,
                naturalLanguageClassifierServicePassword);

        String result = naturalLanguageClassifierHelper.getClassifiedResultByAppState(service, appState, input);
        if (result.equals(CLASSIFIED_RESULT_TRASH)) {
            result = naturalLanguageClassifierHelper.getResultByClassifier(
                    service, globalInstructionClassifierId, input);
        }

        return new NaturalLanguageClassificationServiceResponse(result);
    }
}
