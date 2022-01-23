package com.example.periodtableservice.exceptions;

/**
 * Generic exception regarding Element operations.
 */
public class ElementException extends RuntimeException {

    private static final long serialVersionUID = 3698074121285147597L;

    public ElementException(final String message) {
        super(message);
    }
}
