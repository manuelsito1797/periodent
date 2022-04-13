package com.project.domain.user.exception;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class UsernameIsEmptyException extends RuntimeException {
    public UsernameIsEmptyException(String message) {
        super(message);
    }
}
