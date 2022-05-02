package com.project.app.user.viewmodel;

import com.project.app.di.activej.ActiveJ;
import com.project.app.user.model.permission.FXPermission;
import com.project.domain.user.interactor.GetAllPermissions;
import com.project.domain.user.model.permission.PermissionRequestModel;
import com.project.domain.user.model.permission.PermissionResponseModel;
import com.project.domain.user.presenter.AddPermissionPresenter;
import com.project.domain.user.presenter.PermissionsPresenter;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.BooleanProperty;

/**
 * @author dhelarius 25/4/2022
 * periodent
 */
public class PermissionViewModel implements ViewModel {

    @FunctionalInterface
    public interface Callback {
        void onAddPermission(PermissionResponseModel response, Throwable throwable);
    }

    private final ObservableList<FXPermission> permissions = FXCollections.observableArrayList();

    private final ObjectProperty<FXPermission> permissionProperty = new SimpleObjectProperty<>();

    private final BooleanProperty newPermission = new SimpleBooleanProperty(false);

    private final ObjectProperty<Throwable> throwableProperty = new SimpleObjectProperty<>();

    public ObservableList<FXPermission> getPermissions() {
        return permissions;
    }

    public ObjectProperty<FXPermission> getPermissionProperty() {
        return permissionProperty;
    }

    public FXPermission getPermission() {
        return permissionProperty.get();
    }

    public void setPermission(FXPermission permission) {
        permissionProperty.set(permission);
    }

    public BooleanProperty getNewPermissionProperty() {
        return newPermission;
    }

    public boolean isNewPermission() {
        return newPermission.get();
    }

    public void setNewPermission(boolean isNewPermission) {
        newPermission.set(isNewPermission);
    }

    public ObjectProperty<Throwable> getThrowableProperty() {
        return throwableProperty;
    }

    public Throwable getThrowable() {
        return throwableProperty.get();
    }

    public void addPermission(Callback callback) {
        var addPermissionPresenter = ActiveJ.getInstance(AddPermissionPresenter.class);
        var permission = permissionProperty.get();
        var request = new PermissionRequestModel(permission.getId(), permission.getDescription(),
                permission.getKey(), permission.isActive());

        addPermissionPresenter.show(request, callback::onAddPermission);
    }

    public void updatePermission() {
        System.out.println("Update permission: " + permissionProperty.get());
    }

    public void loadPermissions() {
        var permissionsPresenter = new PermissionsPresenter(ActiveJ.getInstance(GetAllPermissions.class));
        permissionsPresenter.show((response, throwable) -> {
            if(throwable != null) {
                System.err.println(throwable.getMessage());
                return;
            }
            for(var permission : response) {
                permissions.add(new FXPermission(permission.getId(), permission.getDescription(),
                        permission.getKey(), permission.isActive()));
            }
        });
    }
}
