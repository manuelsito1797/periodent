package com.project.domain.user.validator;

import com.project.domain.user.exception.PermitDescriptionException;
import com.project.domain.user.exception.PermitException;
import com.project.domain.user.exception.PermitKeyException;
import com.project.domain.user.model.permission.PermissionRequestModel;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.user.repository.PermissionRepository;

/**
 * @author dhelarius 30/4/2022
 * periodent
 */
public class PermissionValidator {
    public static void validateAdd(PermissionRequestModel requestModel,
                                   PermissionRepository repository) {
        isValidRequest(requestModel);

        if(repository.findByDescription(requestModel.getDescription()) != null) {
            throw new PermitDescriptionException("Existe un permiso con la descripción que ha ingresado, favor ingrese otra descripción");
        }

        if(repository.findByKey(requestModel.getKey()) != null) {
            throw new PermitKeyException("Existe un permiso con la clave que ha ingresado, favor ingrese otra clave");
        }
    }

    public static void validateUpdate(PermissionRequestModel requestModel) {
        isValidRequest(requestModel);
    }

    public static void validateDelete(PermissionRequestModel requestModel, UserRepository userRepository) {
        var users = userRepository.findAll().stream().filter(user -> {
            var permissions = user.getPermissions();
            for (var permission : permissions) {
                if (permission.getId() == requestModel.getId() && permission.isAssigned()) {
                    return true;
                }
            }
            return false;
        }).toList();

        if(users.size() > 0) {
            throw new PermitException("No puede eliminar este permiso porque está asignado a uno o varios usuarios. Si " +
                    "desea eliminarlo quite el permiso a los usuarios que lo tienen asignado.");
        }
    }

    private static void isValidRequest(PermissionRequestModel requestModel) {
        if(!requestModel.isValidDescription()) {
            throw new PermitDescriptionException("Debe ingresar una descripción para el permiso.");
        }

        if(!requestModel.isValidKey()) {
            throw new PermitKeyException("Debe ingresar una clave para el permiso.");
        }
    }
}
