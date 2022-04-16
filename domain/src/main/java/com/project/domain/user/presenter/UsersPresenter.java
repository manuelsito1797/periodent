package com.project.domain.user.presenter;

import com.project.domain.usecase.UseCase;
import com.project.domain.user.interactor.GetAllUsers;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.view.View;

import java.util.List;

/**
 * @author dhelarius 15/4/2022
 * periodent
 */
public class UsersPresenter implements UseCase.Callback<List<UserResponseModel>>{

    private final GetAllUsers getAllUsers;
    private final View<List<UserResponseModel>> view;

    public UsersPresenter(GetAllUsers getAllUsers, View<List<UserResponseModel>> view) {
        this.getAllUsers = getAllUsers;
        this.view = view;
    }

    public void getAllUsers() {
        getAllUsers.execute(this);
    }

    @Override
    public void onSuccess(List<UserResponseModel> result) {
        view.show(result);
    }

    @Override
    public void onError(Throwable throwable) {
        view.showErrorMessage(throwable);
    }
}
