package com.seeworld.api.dictionary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DiscoveryCollectionIdDictionary {
    private static Map<String, String> DISCOVERY_COLLECTION_ID_DICTIONARY = null;

    @Value("${watsonCloudService.discovery.collections.columbus-OH-US.name}")
    private String columbusOhioUsCollectionName;
    @Value("${watsonCloudService.discovery.collections.columbus-OH-US.id}")
    private String columbusOhioUsCollectionId;

    public String getCollectionIdByName(String name) {
        if (DISCOVERY_COLLECTION_ID_DICTIONARY == null) {
            DISCOVERY_COLLECTION_ID_DICTIONARY = buildDiscoveryCollectionIdDictionary();
        }
        return DISCOVERY_COLLECTION_ID_DICTIONARY.get(name);
    }

    private Map<String, String> buildDiscoveryCollectionIdDictionary() {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put(columbusOhioUsCollectionName, columbusOhioUsCollectionId);
        return dictionary;
    }
}
