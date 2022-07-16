package com.project.domain.specialist.validator;

import com.project.domain.specialist.exception.SpecialtyInvalidException;
import com.project.domain.specialist.model.specialty.SpecialtyRequestModel;
import com.project.domain.specialist.repository.SpecialtyRepository;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class SpecialtyValidator {

    public static void validate(SpecialtyRequestModel requestModel) {
        if(!requestModel.isValid()) {
            throw new SpecialtyInvalidException("La descripción de la especialidad esta vacía");
        }
    }

    public static void validateDelete(SpecialtyRequestModel requestModel, SpecialtyRepository repository) {
        var specialties = repository.findAll().stream().filter(specialty -> {
            if(requestModel.getId() == specialty.getId()) {
                return true;
            }
            return false;
        }).toList();

        if(specialties.isEmpty()) {
            throw new SpecialtyInvalidException("No puede eliminar el especialista especificado porqué no existe.");
        }
    }
}
