package com.project.domain.user.exception;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String message) {
        super(message);
    }
}
