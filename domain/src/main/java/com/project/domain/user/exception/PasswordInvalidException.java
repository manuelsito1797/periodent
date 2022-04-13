package com.project.domain.user.exception;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException(String message) {
        super(message);
    }
}
