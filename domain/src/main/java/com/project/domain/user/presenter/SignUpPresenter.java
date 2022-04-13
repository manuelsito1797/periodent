package com.project.domain.user.presenter;

import com.project.domain.usecase.UseCaseWithParam;
import com.project.domain.user.interactor.SignUp;
import com.project.domain.user.model.LoginRequestModel;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.user.preferences.UserPreferences;
import com.project.domain.view.View;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class SignUpPresenter implements UseCaseWithParam.Callback<UserResponseModel> {

    private final SignUp signUp;
    private final View<UserResponseModel> view;

    public SignUpPresenter(SignUp signUp, View<UserResponseModel> view) {
        this.signUp = signUp;
        this.view = view;
    }

    public void signUp(String username, String password) {
        signUp.execute(new LoginRequestModel(username, password), this);
    }

    @Override
    public void onSuccess(UserResponseModel result) {
        UserPreferences.getInstance().setUserPreferences(result);
        view.show(result);
    }

    @Override
    public void onError(Throwable throwable) {
        view.showErrorMessage(throwable);
    }
}
