package com.project.app.user.viewmodel;

import com.project.app.di.activej.ActiveJ;
import com.project.app.user.model.FXUser;
import com.project.domain.user.interactor.GetAllUsers;
import com.project.domain.user.model.UserResponseModel;
import com.project.domain.user.presenter.UsersPresenter;
import com.project.domain.view.View;
import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * @author dhelarius 14/4/2022
 * periodent
 */
public class UserViewModel implements ViewModel, View<List<UserResponseModel>> {

    private final ObservableList<FXUser> users = FXCollections.observableArrayList();

    public ObservableList<FXUser> getUsers() {
        var usersPresenter = new UsersPresenter(ActiveJ.getInstance(GetAllUsers.class), this);
        usersPresenter.getAllUsers();
        return users;
    }

    @Override
    public void show(List<UserResponseModel> value) {
        for(var user: value) {
            users.add(new FXUser(user.getId(), user.getName(), user.getLastname(), user.getDni(),
                    user.getPhone(), user.getEmail(), user.getUsername(), user.getCreatedBy(), user.getCreationDate(),
                    user.isStatus()));
        }
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }
}
