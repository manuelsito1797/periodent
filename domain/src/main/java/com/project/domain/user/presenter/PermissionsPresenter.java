package com.project.domain.user.presenter;

import com.project.domain.user.interactor.GetAllPermissions;
import com.project.domain.user.model.permission.PermissionResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCase;

import java.util.List;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public class PermissionsPresenter implements UseCase.Callback<List<PermissionResponseModel>> {

    private final GetAllPermissions getAllPermissions;

    public PermissionsPresenter(GetAllPermissions getAllPermissions) {
        this.getAllPermissions = getAllPermissions;
    }

    @Override
    public void show(Presenter<List<PermissionResponseModel>> presenter) {
        getAllPermissions.execute(presenter);
    }
}
