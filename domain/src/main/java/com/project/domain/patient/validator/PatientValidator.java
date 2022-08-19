package com.project.domain.patient.validator;

import com.project.domain.patient.exception.PatientInvalidException;
import com.project.domain.patient.model.PatientRequestModel;
import com.project.domain.patient.repository.PatientRepository;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class PatientValidator {
    public static void validate(PatientRequestModel requestModel) {
        if(!requestModel.isValid()) {
            throw new PatientInvalidException("El nombre o apellido del paciente está vacío");
        }
    }

    public static void validateDelete (PatientRequestModel requestModel, PatientRepository repository) {
        var patients = repository.findAll().stream().filter(patient -> {
            if(requestModel.getId() == patient.getId()) {
                return true;
            }
            return false;
        }).toList();

        if(patients.isEmpty()) {
            throw new PatientInvalidException("No puede eliminar el paciente especificado porqué no existe.");
        }
    }
}
