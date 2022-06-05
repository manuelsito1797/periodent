package com.project.domain.user.interactor;

import com.project.domain.mapper.Mapper;
import com.project.domain.security.SecurityAdapter;
import com.project.domain.user.model.CommonUser;
import com.project.domain.user.model.UserRequestModel;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.user.validator.UserValidator;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 4/6/2022
 * periodent
 */
public class AddUser implements UseCaseWithParam<UserRequestModel, UserResponseModel> {

    private final UserRepository repository;
    private final SecurityAdapter securityAdapter;
    private final Mapper mapper;

    public AddUser(UserRepository repository, SecurityAdapter securityAdapter, Mapper mapper) {
        this.repository = repository;
        this.securityAdapter = securityAdapter;
        this.mapper = mapper;
    }

    @Override
    public void execute(UserRequestModel param, Presenter<UserResponseModel> presenter) {
        try {
            UserValidator.validateAdd(param, repository);

            var user = mapper.convertEntityToDto(param, CommonUser.class);
            user.setPermissions(param.getPermissions());

            var encrypt = securityAdapter.encrypt(user.getPassword());
            user.setPassword(encrypt);

            repository.create(user);

            var created = repository.findByUsername(user.getUsername());

            var name = created.getName().concat(" " + created.getLastname());
            var response = new UserResponseModel(created.getId(), created.getName(), created.getLastname(), created.getDni(),
                    created.getPhone(), created.getEmail(), created.getUsername(), name, created.getCreationDate(),
                    created.isStatus());
            response.setPermissions(created.getPermissions());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
