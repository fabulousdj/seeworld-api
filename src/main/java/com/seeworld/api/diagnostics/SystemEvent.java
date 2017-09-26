package com.seeworld.api.diagnostics;

import com.seeworld.api.domain.valueobject.ISystemEvent;

/**
 * System Event Id range is 30000-40000 Created by kpeesari on 2/27/16.
 */
public enum SystemEvent implements ISystemEvent {
    // Validation exceptions [30000 - 30100]
    EPC_SEARCH_SERVICE_INVALID_REQUEST(30000, "EPC search service invalid request"),
    EPC_REINDEX_SERVICE_INVALID_REQUEST(30001, "EPC reindex service invalid request"),

    // Hystrix Exceptions [30101 - 30200]
    HYSTRIX_BAD_REQUEST_ERROR(30101, "Hystrix bad request exception"),
    HYSTRIX_RUNTIME_EXCEPTION(30102, "Hystrix runtime exception"),

    // Service exceptions [30501 - 30999]
    EPC_SEARCH_UNKNOWN_EXCEPTION(30501, "Error while trying to get the Elasticsearch Service"),
    EPC_SEARCH_GET_MATCHING_RESULTS_CONNECTOR_EXCEPTION(30503,
            "failed to get matching results in search service getMatchingResults connector"
                    + " to AWS-hosted Elasticsearch cluster."),
    EPC_SEARCH_BULK_INDEX_CONNECTOR_EXCEPTION(30504,
            "failed to complete bulk index in reindex service bulkIndex connector"
                    + " to AWS-hosted Elasticsearch cluster."),
    EPC_SEARCH_CREATE_INDEX_CONNECTOR_EXCEPTION(30505,
            "failed to create index in reindex service createIndex connector"
                    + " to AWS-hosted Elasticsearch cluster."),
    EPC_SEARCH_DELETE_INDEX_CONNECTOR_EXCEPTION(30506,
            "failed to delete index in reindex service deleteIndex connector"
                    + " to AWS-hosted Elasticsearch cluster."),
    EPC_SEARCH_RETRIEVE_ALIAS_CONNECTOR_EXCEPTION(30507,
            "failed to retrieve alias in reindex service retrieveAlias connector"
                    + " to AWS-hosted Elasticsearch cluster."),
    EPC_SEARCH_RENAME_ALIAS_CONNECTOR_EXCEPTION(30508,
            "failed to rename alias in reindex service renameAlias connector"
                    + " to AWS-hosted Elasticsearch cluster."),
    EPC_SEARCH_ADD_ALIAS_CONNECTOR_EXCEPTION(30509,
            "failed to add alias in reindex service addAlias connector"
                    + " to AWS-hosted Elasticsearch cluster."),
    // ClientID and Authorization exceptions [31000 - 31100]
    API_UNKNOWN_ERROR(31000, "Unknown exception in micro service"),
    API_CLIENT_ID_MISSING(31001, "Client ID not sent in header"),

    // CORS request authentication error [31101-31200]
    CORS_AUTHENTICATION_EXCEPTION(31101, "CORS Authentication Exception.");

    private final int id;
    private final String description;

    SystemEvent(
            int id,
            String description) {
        this.id = id;
        this.description = description;
    }

    public static boolean isInputValidationError(int id) {
        return id >= 30000 && id <= 30101;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
