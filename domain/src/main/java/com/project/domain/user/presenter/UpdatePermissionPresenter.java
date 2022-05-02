package com.project.domain.user.presenter;

import com.project.domain.user.interactor.UpdatePermission;
import com.project.domain.user.model.permission.PermissionRequestModel;
import com.project.domain.user.model.permission.PermissionResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 2/5/2022
 * periodent
 */
public class UpdatePermissionPresenter implements
        UseCaseWithParam.Callback<PermissionRequestModel, PermissionResponseModel> {

    private final UpdatePermission updatePermission;

    public UpdatePermissionPresenter(UpdatePermission updatePermission) {
        this.updatePermission = updatePermission;
    }

    @Override
    public void show(PermissionRequestModel param, Presenter<PermissionResponseModel> presenter) {
        updatePermission.execute(param, presenter);
    }
}
