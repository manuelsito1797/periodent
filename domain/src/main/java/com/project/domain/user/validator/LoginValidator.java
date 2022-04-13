package com.project.domain.user.validator;

import com.project.domain.security.SecurityAdapter;
import com.project.domain.user.exception.InvalidLoginException;
import com.project.domain.user.exception.PasswordInvalidException;
import com.project.domain.user.exception.UserDoesNotExistException;
import com.project.domain.user.exception.UsernameIsEmptyException;
import com.project.domain.user.model.LoginRequestModel;
import com.project.domain.user.repository.UserRepository;

import java.security.GeneralSecurityException;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class LoginValidator {

    public static void validate(LoginRequestModel requestModel, UserRepository repository,
                                SecurityAdapter securityAdapter) {
        if(!requestModel.usernameIsValid()) {
            throw new UsernameIsEmptyException("No se ha proporcionado un nombre de usuario. Favor ingresar nombre de usuario.");
        }

        if(repository.findByUsername(requestModel.getUsername()) == null) {
            throw new UserDoesNotExistException("El usuario " + requestModel.getUsername() + " no existe.");
        }

        if(!requestModel.passwordIsValid()) {
            throw new PasswordInvalidException("La contraseña debe poseer 4 caracteres o más.");
        }

        /*
         Desencriptar la contraseña del usuario y verificar que coincida con la
         contraseña dada por el mismo
         */
        var user = repository.findByUsername(requestModel.getUsername());
        var encrypted = user.getPassword();
        String decrypted = null;
        try {
            decrypted = securityAdapter.decrypt(encrypted);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if(!requestModel.getPassword().equals(decrypted)) {
            throw new InvalidLoginException("Error al intentar iniciar sesión. Revise su usuario y contraseña.");
        }
    }
}
