package com.seeworld.api.domain.service;

import com.seeworld.api.domain.valueobject.ConversationContext;
import com.seeworld.api.domain.valueobject.ConversationServiceResponse;
import com.seeworld.api.domain.valueobject.PlaceSearchServiceResponse;

public interface IPlaceSearchService {
    PlaceSearchServiceResponse search(String input, float latitude, float longitude);
}
