package com.project.domain.user.presenter;

import com.project.domain.user.interactor.AddUser;
import com.project.domain.user.model.UserRequestModel;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 4/6/2022
 * periodent
 */
public class AddUserPresenter implements
        UseCaseWithParam.Callback<UserRequestModel, UserResponseModel> {

    private final AddUser addUser;

    public AddUserPresenter(AddUser addUser) {
        this.addUser = addUser;
    }

    @Override
    public void show(UserRequestModel param, Presenter<UserResponseModel> presenter) {
        addUser.execute(param, presenter);
    }
}
