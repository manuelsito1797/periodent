package com.project.app.di.activej;

import com.project.adapter.converter.modelmapper.EntityDtoConverter;
import com.project.adapter.security.cipher.CipherAdapter;
import com.project.app.LoginApp;
import com.project.app.controller.LoginDialogController;
import com.project.data.user.DSUser;
import com.project.data.user.UserRepositoryImpl;
import com.project.data.user.permission.DSPermission;
import com.project.data.user.permission.PermissionRepositoryImpl;
import com.project.data.user.permission.UserPermissionDao;
import com.project.domain.gateway.DsGateway;
import com.project.domain.security.SecurityAdapter;
import com.project.domain.user.interactor.AddPermission;
import com.project.domain.user.interactor.GetAllPermissions;
import com.project.domain.user.interactor.GetAllUsers;
import com.project.domain.user.interactor.SignUp;
import com.project.domain.user.model.UserDsRequestModel;
import com.project.domain.user.model.permission.PermissionDsRequestModel;
import com.project.domain.user.presenter.AddPermissionPresenter;
import com.project.domain.user.repository.PermissionRepository;
import com.project.domain.user.repository.UserRepository;
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
        // Datasources
        @Provides
        DsGateway<UserDsRequestModel> dsUser() {
            return new DSUser();
        }

        @Provides
        DsGateway<PermissionDsRequestModel> dsPermissionRequest() {
            return new DSPermission();
        }

        @Provides
        EntityDtoConverter converter() {
            return new EntityDtoConverter();
        }

        @Provides
        SecurityAdapter securityAdapter() {
            return new CipherAdapter();
        }

        @Provides
        UserPermissionDao dsPermission() {
            return new UserPermissionDao();
        }

        @Provides
        UserRepository userRepository(DsGateway<UserDsRequestModel> dsUser, UserPermissionDao userPermissionDao,
                                      EntityDtoConverter converter) {
            return new UserRepositoryImpl(dsUser, userPermissionDao, converter);
        }

        @Provides
        PermissionRepository permissionRepository(DsGateway<PermissionDsRequestModel> dsPermissionRequest,
                                                  EntityDtoConverter converter) {
            return new PermissionRepositoryImpl(dsPermissionRequest, converter);
        }

        // Presenter
        @Provides
        SignUp signUp(UserRepository userRepository, SecurityAdapter securityAdapter) {
            return new SignUp(userRepository, securityAdapter);
        }

        @Provides
        LoginApp loginApp() {
            return new LoginApp();
        }

        @Provides
        GetAllUsers getAllUsers(UserRepository repository) {
             return new GetAllUsers(repository);
        }

        @Provides
        GetAllPermissions getAllPermissions(PermissionRepository repository) {
            return new GetAllPermissions(repository);
        }

        @Provides
        AddPermission addPermission(PermissionRepository repository) {
            return new AddPermission(repository);
        }

        @Provides
        AddPermissionPresenter addPermissionPresenter(AddPermission addPermission) {
            return new AddPermissionPresenter(addPermission);
        }

        // Views
        @Provides
        LoginDialogController loginDialogController() {
            return new LoginDialogController();
        }
    };

    public static <T> T getInstance(Class<T> clazz) {
        return Injector.of(app).getInstance(clazz);
    }
}
