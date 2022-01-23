package com.example.periodtableservice.exceptions.handlers;

import com.example.periodtableservice.controllers.ElementController;
import com.example.periodtableservice.exceptions.ElementException;
import com.example.periodtableservice.exceptions.messages.ExceptionMessages;
import com.example.periodtableservice.exceptions.responses.GenericExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

/**
 * Used to handle all of the exceptions around operations with the Element.
 */
@ControllerAdvice(assignableTypes = ElementController.class)
public class ElementExceptionHandler {

    @ExceptionHandler(value = ElementException.class)
    protected ResponseEntity<GenericExceptionResponse> handleElementException(final ElementException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        //Check the null case, to add proper response code along with the message
        if(ex.getMessage().contains(ExceptionMessages.ELEMENTS_FIND_BY_ATOMIC_NUMBER_NULL)){
            httpStatus = HttpStatus.OK;
        }
        final GenericExceptionResponse response = GenericExceptionResponse.builder()
                .message(ex.getMessage())
                .occurred(Instant.now())
                .status(httpStatus)
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

}
