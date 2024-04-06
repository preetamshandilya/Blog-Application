package com.test.token.jwtoken.exception;

import java.io.Serial;

public class ResourceNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    String resourceName;
    String fieldName;
    Integer fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Integer userId) {
        super(String.format("%s Not Found With This %s : %s", resourceName, fieldName, userId));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = userId;
    }
}
