package com.project.domain.user.exception;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
