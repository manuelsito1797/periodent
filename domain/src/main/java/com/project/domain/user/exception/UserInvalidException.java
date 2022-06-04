package com.project.domain.user.exception;

/**
 * @author dhelarius 28/5/2022
 * periodent
 */
public class UserInvalidException extends RuntimeException {
    public UserInvalidException(String message) {
        super(message);
    }
}
