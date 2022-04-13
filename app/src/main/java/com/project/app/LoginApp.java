package com.project.app;

import com.project.app.di.activej.ActiveJ;
import com.project.domain.user.interactor.SignUp;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.user.preferences.UserPreferences;
import com.project.domain.user.presenter.SignUpPresenter;
import com.project.domain.view.View;

/**
 * @author dhelarius 11/4/2022
 * periodent
 */
public class LoginApp implements View<UserResponseModel> {

    public void execute() {
        var signUp = ActiveJ.getInstance(SignUp.class);
        var signUpPresenter = new SignUpPresenter(signUp, this);
        signUpPresenter.signUp("etavarez", "1234");
    }

    @Override
    public void show(UserResponseModel value) {
        var userPreferences = UserPreferences.getInstance();
        var userFromPreferences = userPreferences.getUserFromPreference();
        System.out.println("USER FROM USE CASE: " + value.toString());
        System.out.println("USER FROM PREFERENCES: " + userFromPreferences.toString());
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }
}
