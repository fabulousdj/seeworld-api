package com.seeworld.api.domain.service.impl;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import com.seeworld.api.diagnostics.SystemEvent;
import com.seeworld.api.domain.service.INaturalLanguageProcessingService;
import com.seeworld.api.domain.service.helper.NaturalLanguageClassifierHelper;
import com.seeworld.api.domain.validator.NaturalLanguageClassifierRequestValidator;
import com.seeworld.api.domain.valueobject.ErrorDetails;
import com.seeworld.api.domain.valueobject.ISystemEvent;
import com.seeworld.api.domain.valueobject.NaturalLanguageClassificationServiceResponse;
import com.seeworld.api.domain.valueobject.NaturalLanguageUnderstandingServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NaturalLanguageProcessingService implements INaturalLanguageProcessingService {

    private static String CLASSIFIED_RESULT_TRASH = "trash";

    @Value("${watsonCloudService.naturalLanguageClassifier.username}")
    private String naturalLanguageClassifierServiceUsername;
    @Value("${watsonCloudService.naturalLanguageClassifier.password}")
    private String naturalLanguageClassifierServicePassword;
    @Value("${watsonCloudService.naturalLanguageClassifier.id.globalInstructionClassifier}")
    private String globalInstructionClassifierId;

    @Value("${watsonCloudService.naturalLanguageUnderstanding.username}")
    private String naturalLanguageUnderstandingServiceUsername;
    @Value("${watsonCloudService.naturalLanguageUnderstanding.password}")
    private String naturalLanguageUnderstandingServicePassword;

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

    @Override
    public NaturalLanguageUnderstandingServiceResponse understandDestination(String input) {
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                naturalLanguageUnderstandingServiceUsername,
                naturalLanguageUnderstandingServicePassword);

        KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                .emotion(true)
                .sentiment(true)
                .limit(2)
                .build();

        Features features = new Features.Builder()
                .keywords(keywordsOptions)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(input)
                .features(features)
                .build();

        AnalysisResults response = service
                .analyze(parameters)
                .execute();

        System.out.println(response);
        List<KeywordsResult> keywordsResults = response.getKeywords();
        StringBuilder destinationBuilder = new StringBuilder();
        for (int i = 0; i < keywordsResults.size(); i++) {
            KeywordsResult keyword = keywordsResults.get(i);
            destinationBuilder.append(keyword.getText());
            if (i < keywordsResults.size() - 1) {
                destinationBuilder.append(" ");
            }
        }
        return new NaturalLanguageUnderstandingServiceResponse(destinationBuilder.toString());
    }
}
