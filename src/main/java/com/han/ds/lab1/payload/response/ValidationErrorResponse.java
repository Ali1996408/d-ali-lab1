package com.han.ds.lab1.payload.response;

import lombok.*;
import java.util.Map;

@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {
    private String message;
    private final Map<String, String> errors;

    public ValidationErrorResponse(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
