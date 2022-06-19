package com.project.app.di.activej;

import com.project.adapter.converter.modelmapper.EntityDtoConverter;
import com.project.adapter.security.cipher.CipherAdapter;
import com.project.app.LoginApp;
import com.project.app.controller.LoginDialogController;
import com.project.data.user.DSUser;
import com.project.data.user.UserDao;
import com.project.data.user.UserRepositoryImpl;
import com.project.data.user.permission.DSPermission;
import com.project.data.user.permission.PermissionRepositoryImpl;
import com.project.data.user.permission.UserPermissionDao;
import com.project.domain.gateway.DsGateway;
import com.project.domain.mapper.Mapper;
import com.project.domain.security.SecurityAdapter;
import com.project.domain.user.interactor.*;
import com.project.domain.user.model.UserDsRequestModel;
import com.project.domain.user.model.permission.PermissionDsRequestModel;
import com.project.domain.user.presenter.*;
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
        Mapper converter() {
            return new EntityDtoConverter();
        }

        @Provides
        SecurityAdapter securityAdapter() {
            return new CipherAdapter();
        }

        @Provides
        UserDao userDao() {
            return new UserDao();
        }

        @Provides
        UserPermissionDao dsPermission() {
            return new UserPermissionDao();
        }

        @Provides
        UserRepository userRepository(DsGateway<UserDsRequestModel> dsUser, UserDao userDao,
                                      UserPermissionDao userPermissionDao, Mapper converter) {
            return new UserRepositoryImpl(dsUser, userDao, userPermissionDao, converter);
        }

        @Provides
        PermissionRepository permissionRepository(DsGateway<PermissionDsRequestModel> dsPermissionRequest,
                                                  Mapper converter) {
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
        AddUser addUser(UserRepository repository, SecurityAdapter securityAdapter, Mapper converter) {
            return new AddUser(repository, securityAdapter, converter);
        }

        @Provides
        AddUserPresenter addUserPresenter(AddUser addUser) {
            return new AddUserPresenter(addUser);
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

        @Provides
        UpdatePermission updatePermission(PermissionRepository repository) {
            return new UpdatePermission(repository);
        }

        @Provides
        UpdatePermissionPresenter updatePermissionPresenter(UpdatePermission updatePermission) {
            return new UpdatePermissionPresenter(updatePermission);
        }

        @Provides
        DeletePermission deletePermission(UserRepository userRepository, PermissionRepository permissionRepository) {
            return new DeletePermission(userRepository, permissionRepository);
        }

        @Provides
        DeletePermissionPresenter deletePermissionPresenter(DeletePermission deletePermission) {
            return new DeletePermissionPresenter(deletePermission);
        }

        @Provides
        UpdateUser updateUser(UserRepository repository, SecurityAdapter securityAdapter) {
            return new UpdateUser(repository, securityAdapter);
        }

        @Provides
        UpdateUserPresenter updateUserPresenter(UpdateUser updateUser) {
            return new UpdateUserPresenter(updateUser);
        }

        @Provides
        DeleteUser deleteUser(UserRepository repository, Mapper mapper) {
            return new DeleteUser(repository, mapper);
        }

        @Provides
        DeleteUserPresenter deleteUserPresenter(DeleteUser deleteUser) {
            return new DeleteUserPresenter(deleteUser);
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
