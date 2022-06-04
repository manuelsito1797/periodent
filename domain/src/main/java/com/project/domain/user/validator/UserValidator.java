package com.project.domain.user.validator;

import com.project.domain.user.exception.UserInvalidException;
import com.project.domain.user.model.UserRequestModel;

/**
 * @author dhelarius 28/5/2022
 * periodent
 */
public class UserValidator {

    public static void validateUpdate(UserRequestModel requestModel) {
        if(!requestModel.isValid()) {
            throw new UserInvalidException("El nombre o apellido no pueden estar vacíos.");
        }

        if(!requestModel.isValidUsername()) {
            throw new UserInvalidException("El usuario no puede estar vacío.");
        }

        if(!requestModel.getPassword().isEmpty() && requestModel.getPassword().length() < 4) {
            throw new UserInvalidException("La contraseña introducida no es válida. Debe tener 4 carácteres o más");
        }
    }
}
