package com.hospital.exception;

/**
 * Exception thrown when validation of input data fails
 */
public class ValidationException extends RuntimeException {
    private String fieldName;
    private Object rejectedValue;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String fieldName, Object rejectedValue, String message) {
        super(message);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }
}
