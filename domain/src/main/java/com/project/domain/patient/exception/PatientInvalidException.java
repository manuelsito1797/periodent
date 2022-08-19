package com.project.domain.patient.exception;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class PatientInvalidException extends RuntimeException {
    public PatientInvalidException(String message) {
        super(message);
    }
}
