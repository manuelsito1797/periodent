package com.project.domain.user.interactor;

import com.project.domain.security.SecurityAdapter;
import com.project.domain.user.model.CommonUser;
import com.project.domain.user.model.UserRequestModel;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.user.validator.UserValidator;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 28/5/2022
 * periodent
 */
public class UpdateUser implements UseCaseWithParam<UserRequestModel, UserResponseModel> {

    private final UserRepository repository;
    private final SecurityAdapter securityAdapter;

    public UpdateUser(UserRepository repository, SecurityAdapter securityAdapter) {
        this.repository = repository;
        this.securityAdapter = securityAdapter;
    }

    @Override
    public void execute(UserRequestModel param, Presenter<UserResponseModel> presenter) {
        try {
            UserValidator.validateUpdate(param);

            var user = new CommonUser(param.getId(), param.getName(), param.getLastname(),
                    param.getDni(), param.getPhone(), param.getEmail(), param.getUsername(), param.getPassword(),
                    param.getCreatedBy(), param.getCreationDate(), param.isStatus());
            user.setPermissions(param.getPermissions());

            // Actualizar password si no está vacío
            if(!user.getPassword().isEmpty()) {
                 var encrypt = securityAdapter.encrypt(user.getPassword());
                 user.setPassword(encrypt);
            }

            repository.update(user);

            var name = user.getName().concat(" " + user.getLastname());
            var response = new UserResponseModel(user.getId(), user.getName(), user.getLastname(), user.getDni(),
                    user.getPhone(), user.getEmail(), user.getUsername(), name,
                    user.getCreationDate(), user.isStatus());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
