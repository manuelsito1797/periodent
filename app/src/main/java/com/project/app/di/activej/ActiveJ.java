package com.project.app.di.activej;

import com.project.adapter.converter.modelmapper.EntityDtoConverter;
import com.project.adapter.security.cipher.CipherAdapter;
import com.project.app.LoginApp;
import com.project.app.controller.LoginDialogController;
import com.project.data.user.DSUser;
import com.project.data.user.UserRepositoryImpl;
import com.project.domain.gateway.DsGateway;
import com.project.domain.security.SecurityAdapter;
import com.project.domain.user.interactor.SignUp;
import com.project.domain.user.model.UserDsRequestModel;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.user.preferences.UserPreferences;
import com.project.domain.user.presenter.SignUpPresenter;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.view.View;
import io.activej.inject.Injector;
import io.activej.inject.annotation.Provides;
import io.activej.inject.module.AbstractModule;
import io.activej.inject.module.Module;

/**
 * @author dhelarius 11/4/2022
 * periodent
 */
public class ActiveJ {

    static Module app = new AbstractModule() {
        @Provides
        UserPreferences userPreferences() {
            return new UserPreferences();
        }

        @Provides
        DsGateway<UserDsRequestModel> dsUser() {
            return new DSUser();
        }

        @Provides
        EntityDtoConverter converter() {
            return new EntityDtoConverter();
        }

        @Provides
        UserRepository userRepository(DsGateway<UserDsRequestModel> dsUser, EntityDtoConverter converter) {
            return new UserRepositoryImpl(dsUser, converter);
        }

        @Provides
        SecurityAdapter securityAdapter() {
            return new CipherAdapter();
        }

        @Provides
        SignUp signUp(UserRepository userRepository, SecurityAdapter securityAdapter) {
            return new SignUp(userRepository, securityAdapter);
        }

        // Presenter
        @Provides
        View<UserResponseModel> loginApp() {
            return new LoginDialogController();
        }

        @Provides
        SignUpPresenter signUpPresenter(SignUp signUp, View<UserResponseModel> loginDialogController) {
            return new SignUpPresenter(signUp, loginDialogController);
        }
    };

    public static <T> T getInstance(Class<T> clazz) {
        return Injector.of(app).getInstance(clazz);
    }
}
