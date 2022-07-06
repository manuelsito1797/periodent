package com.project.domain.patient.model;

import java.sql.Date;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class FactoryPatient {

    public static Patient createPatient(PatienType type, int id, String name, String lastname, Date birthday, String dni,
                                        String phone, String address, String email, int createdBy,
                                        String createdByName, boolean active) {

        switch (type) {
            case COMMON_PATIENT: return new CommonPatient(id, name, lastname, birthday, dni, phone,
                    address, email, createdBy, active);
            case PATIENT_REQUEST_MODEL: return new PatientRequestModel(id, name, lastname, birthday, dni, phone,
                    address, email, createdBy, active);
            case PATIENT_RESPONSE_MODEL: return new PatientResponseModel(id, name, lastname, birthday, dni, phone,
                    address, email, createdBy, createdByName, active);
            default: return null;
        }
    }
}
