package com.hospital.exception;

/**
 * Exception thrown when attempting to create a user with an email that already exists
 */
public class UserAlreadyExistsException extends RuntimeException {
    private String email;

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String email, String message) {
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
