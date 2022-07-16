package com.project.domain.specialist.validator;

import com.project.domain.specialist.exception.SpecialistInvalidException;
import com.project.domain.specialist.model.specialist.SpecialistRequestModel;
import com.project.domain.specialist.repository.SpecialistRepository;

/**
 * @author dhelarius 7/7/2022
 * periodent
 */
public class SpecialistValidator {

    public static void validate(SpecialistRequestModel requestModel) {
        if(!requestModel.isValid()) {
            throw new SpecialistInvalidException("El nombre o apellido del especialista está vacío");
        }
    }

    public static void validateDelete(SpecialistRequestModel requestModel, SpecialistRepository repository) {
        var specialists = repository.findAll().stream().filter(specialist -> {
            if(requestModel.getId() == specialist.getId()) {
                return true;
            }
            return false;
        }).toList();

        if(specialists.isEmpty()) {
            throw new SpecialistInvalidException("No puede eliminar el especialista especificado porqué no existe.");
        }
    }
}
