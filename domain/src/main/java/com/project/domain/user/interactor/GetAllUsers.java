package com.project.domain.user.interactor;

import com.project.domain.usecase.UseCase;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dhelarius 15/4/2022
 * periodent
 */
public class GetAllUsers implements UseCase<List<UserResponseModel>> {

    private final UserRepository repository;

    public GetAllUsers(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Callback<List<UserResponseModel>> callback) {
        List<UserResponseModel> userResponseModels = new ArrayList<>();
        try {
            var users = repository.findAll();

            for (var user: users) {
                userResponseModels.add(new UserResponseModel(user.getId(), user.getName(), user.getLastname(),
                        user.getDni(), user.getPhone(), user.getEmail(), user.getUsername(), user.getCreatedBy(),
                        user.getCreationDate(), user.isStatus()));
            }

            callback.onSuccess(userResponseModels);
        } catch (Throwable throwable) {
            callback.onError(throwable);
        }
    }
}
