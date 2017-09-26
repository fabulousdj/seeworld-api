package com.seeworld.api.domain.valueobject;

public interface IResponseMessage<T> {

    T getValue();

    ErrorDetails getErrorDetails();

    boolean isSuccessful();
}