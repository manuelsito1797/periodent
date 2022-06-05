package com.project.app.user.viewmodel;

import com.project.app.di.activej.ActiveJ;
import com.project.app.user.model.FXUser;
import com.project.domain.mapper.Mapper;
import com.project.domain.user.interactor.GetAllUsers;
import com.project.domain.user.model.UserRequestModel;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.user.model.permission.UserPermission;
import com.project.domain.user.preferences.UserPreferences;
import com.project.domain.user.presenter.AddUserPresenter;
import com.project.domain.user.presenter.UpdateUserPresenter;
import com.project.domain.user.presenter.UsersPresenter;
import com.project.domain.view.View;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * @author dhelarius 14/4/2022
 * periodent
 */
public class UserViewModel implements ViewModel, View<List<UserResponseModel>> {

    private final String LOG = this.getClass().getSimpleName() + ": ";

    private final ObservableList<FXUser> users = FXCollections.observableArrayList();

    private final ObjectProperty<FXUser> userProperty = new SimpleObjectProperty<>();

    private final BooleanProperty newUser = new SimpleBooleanProperty(false);

    public BooleanProperty getNewUserProperty() {
        return newUser;
    }

    public void setNewUser(boolean isNewUser) {
        newUser.set(isNewUser);
    }

    public boolean isNewUser() {
        return newUser.get();
    }

    public void loadUsers() {
        var usersPresenter = new UsersPresenter(ActiveJ.getInstance(GetAllUsers.class), this);
        usersPresenter.getAllUsers();
    }

    public ObservableList<FXUser> getUsers() {
        return users;
    }

    public ObjectProperty<FXUser> getUserProperty() {
        return userProperty;
    }

    public FXUser getUser() {
        return userProperty.get();
    }

    public void setUser(FXUser user) {
        this.userProperty.set(user);
    }

    @Override
    public void show(List<UserResponseModel> value) {
        for(var user: value) {
            users.add(new FXUser(user.getId(), user.getName(), user.getLastname(), user.getDni(),
                    user.getPhone(), user.getEmail(), user.getUsername(), "", user.getCreatedBy(),
                    user.getCreationDate(), user.isStatus(), user.getPermissions()));
        }
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }

    public void save(Callback<UserResponseModel> callback) {
        var presenter = ActiveJ.getInstance(AddUserPresenter.class);
        var user = userProperty.get();

        var request = getUserRequest(user);

        for(var permission : user.getPermissions()) {
            request.getPermissions().add(new UserPermission(permission.getId(),
                    permission.getDescription(), permission.getKey(), permission.isAssigned()));
        }

        presenter.show(request, callback::onPresent);
    }

    public void update(Callback<UserResponseModel> callback) {
        var presenter = ActiveJ.getInstance(UpdateUserPresenter.class);
        var user = userProperty.get();

        var pref = UserPreferences.getInstance();
        var userPref = pref.getUserFromPreference();

        var request = new UserRequestModel(user.getId(), user.getName(), user.getLastname(), user.getDni(),
                user.getPhone(), user.getEmail(), user.getUsername(), user.getPassword(), userPref.getId(),
                user.getCreationDate(), user.isStatus());

        for(var permission : user.getPermissions()) {
            request.getPermissions().add(new UserPermission(permission.getId(),
                    permission.getDescription(), permission.getKey(), permission.isAssigned()));
        }

        presenter.show(request, callback::onPresent);
    }

    private UserRequestModel getUserRequest(FXUser user) {
        var pref = UserPreferences.getInstance();
        var userPref = pref.getUserFromPreference();

        return new UserRequestModel(user.getId(), user.getName(), user.getLastname(), user.getDni(),
                user.getPhone(), user.getEmail(), user.getUsername(), user.getPassword(), userPref.getId(),
                user.getCreationDate(), user.isStatus());
    }
}
