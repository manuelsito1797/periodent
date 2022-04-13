package com.project.domain.user.interactor;

import com.project.domain.security.SecurityAdapter;
import com.project.domain.usecase.UseCaseWithParam;
import com.project.domain.user.model.LoginRequestModel;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.user.validator.LoginValidator;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class SignUp implements UseCaseWithParam<LoginRequestModel, UserResponseModel> {

    private final UserRepository repository;
    private final SecurityAdapter securityAdapter;

    public SignUp(UserRepository repository, SecurityAdapter securityAdapter) {
        this.repository = repository;
        this.securityAdapter = securityAdapter;
    }

    @Override
    public void execute(LoginRequestModel param, Callback<UserResponseModel> callback) {
        try {
            LoginValidator.validate(param, repository, securityAdapter);

            var user = repository.findByUsername(param.getUsername());

            var response = new UserResponseModel(user.getId(), user.getName(), user.getLastname(),
                    user.getDni(), user.getPhone(), user.getEmail(), user.getUsername(), user.getCreatedBy(),
                    user.getCreationDate(), user.isStatus());

            callback.onSuccess(response);
        } catch(final Throwable throwable) {
            callback.onError(throwable);
        }
    }
}
