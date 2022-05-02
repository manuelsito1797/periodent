package com.project.domain.user.validator;

import com.project.domain.user.exception.PermitDescriptionException;
import com.project.domain.user.exception.PermitKeyException;
import com.project.domain.user.model.permission.PermissionRequestModel;
import com.project.domain.user.repository.PermissionRepository;

/**
 * @author dhelarius 30/4/2022
 * periodent
 */
public class PermissionValidator {
    public static void validate(PermissionRequestModel requestModel,
                                PermissionRepository repository) {
        if(!requestModel.isValidDescription()) {
            throw new PermitDescriptionException("Debe ingresar una descripción para el permiso.");
        }

        if(!requestModel.isValidKey()) {
            throw new PermitKeyException("Debe ingresar una clave para el permiso.");
        }

        if(repository.findByDescription(requestModel.getDescription()) != null) {
            throw new PermitDescriptionException("Existe un permiso con la descripción que ha ingresado, favor ingrese otra descripción");
        }

        if(repository.findByKey(requestModel.getKey()) != null) {
            throw new PermitKeyException("Existe un permiso con la clave que ha ingresado, favor ingrese otra clave");
        }
    }
}
