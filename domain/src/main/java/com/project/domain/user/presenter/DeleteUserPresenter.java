package com.project.domain.user.presenter;

import com.project.domain.user.interactor.DeleteUser;
import com.project.domain.user.model.UserRequestModel;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 11/6/2022
 * periodent
 */
public class DeleteUserPresenter implements
        UseCaseWithParam.Callback<UserRequestModel, UserResponseModel> {

    private final DeleteUser deleteUser;

    public DeleteUserPresenter(DeleteUser deleteUser) {
        this.deleteUser = deleteUser;
    }

    @Override
    public void show(UserRequestModel param, Presenter<UserResponseModel> presenter) {
        deleteUser.execute(param, presenter);
    }
}
