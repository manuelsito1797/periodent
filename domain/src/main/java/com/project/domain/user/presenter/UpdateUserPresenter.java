package com.project.domain.user.presenter;

import com.project.domain.user.interactor.UpdateUser;
import com.project.domain.user.model.UserRequestModel;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 29/5/2022
 * periodent
 */
public class UpdateUserPresenter implements
        UseCaseWithParam.Callback<UserRequestModel, UserResponseModel> {

    private final UpdateUser updateUser;

    public UpdateUserPresenter(UpdateUser updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public void show(UserRequestModel param, Presenter<UserResponseModel> presenter) {
        updateUser.execute(param, presenter);
    }
}
