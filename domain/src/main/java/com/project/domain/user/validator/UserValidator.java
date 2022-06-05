package com.project.domain.user.validator;

import com.project.domain.user.exception.UserInvalidException;
import com.project.domain.user.model.UserRequestModel;
import com.project.domain.user.repository.UserRepository;

/**
 * @author dhelarius 28/5/2022
 * periodent
 */
public class UserValidator {

    public static void validateAdd(UserRequestModel requestModel, UserRepository repository) {

        if(repository.existByUsername(requestModel.getUsername())) {
            throw new UserInvalidException("Existe un usuario con el nombre de usuario que ha ingresado");
        }

        validate(requestModel);

        if(!requestModel.isValidPassword()) {
            throw new UserInvalidException("La contraseña proporcionada debe tener 4 caracteres o más");
        }
    }

    public static void validateUpdate(UserRequestModel requestModel) {
        validate(requestModel);

        if(!requestModel.getPassword().isEmpty() && requestModel.getPassword().length() < 4) {
            throw new UserInvalidException("La contraseña introducida no es válida. Debe tener 4 carácteres o más");
        }
    }

    private static void validate(UserRequestModel requestModel) {
        if(!requestModel.isValid()) {
            throw new UserInvalidException("El nombre o apellido no pueden estar vacíos.");
        }

        if(!requestModel.isValidUsername()) {
            throw new UserInvalidException("El usuario no puede estar vacío.");
        }
    }
}
