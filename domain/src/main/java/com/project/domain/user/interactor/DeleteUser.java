package com.project.domain.user.interactor;

import com.project.domain.mapper.Mapper;
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
public class DeleteUser implements UseCaseWithParam<UserRequestModel, UserResponseModel> {

    private final UserRepository repository;
    private final Mapper mapper;

    public DeleteUser(UserRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void execute(UserRequestModel param, Presenter<UserResponseModel> presenter) {
        try {
            UserValidator.validateDelete(param, repository);

            var user = mapper.convertEntityToDto(param, CommonUser.class);

            repository.delete(user.getId());

            var createdBy = user.getName().concat(" " + user.getLastname());
            var response = new UserResponseModel(user.getId(), user.getName(), user.getLastname(), user.getDni(),
                    user.getPhone(), user.getEmail(), user.getUsername(), createdBy, user.getCreationDate(), user.isStatus());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
