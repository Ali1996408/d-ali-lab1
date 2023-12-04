package com.han.ds.lab1.controller;

import com.han.ds.lab1.payload.response.ErrorResponse;
import com.han.ds.lab1.payload.response.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Hidden
@RestController
public class ErrorController {
    private final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponse badRequest(MethodArgumentNotValidException exception) {
        var validationErrors = prepareValidationErrors(exception.getBindingResult().getFieldErrors());
        logger.info("Bad Request " + validationErrors);
        return new ValidationErrorResponse("Validation failed", validationErrors);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse notFound(EntityNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse error(RuntimeException exception) {
        logger.error("", exception);
        return new ErrorResponse(exception.getMessage());
    }


    private Map<String, String> prepareValidationErrors(List<FieldError> errors) {
        Map<String, String> result = new HashMap<>();
        for (FieldError error : errors) {
            result.put(error.getField(), "Field has wrong value " + error.getRejectedValue() + " : " + error.getDefaultMessage());
        }
        return result;
    }
}
