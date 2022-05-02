package com.project.domain.user.presenter;

import com.project.domain.user.interactor.DeletePermission;
import com.project.domain.user.model.permission.PermissionRequestModel;
import com.project.domain.user.model.permission.PermissionResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 2/5/2022
 * periodent
 */
public class DeletePermissionPresenter implements
        UseCaseWithParam.Callback<PermissionRequestModel, PermissionResponseModel> {

    private final DeletePermission deletePermission;

    public DeletePermissionPresenter(DeletePermission deletePermission) {
        this.deletePermission = deletePermission;
    }

    @Override
    public void show(PermissionRequestModel param, Presenter<PermissionResponseModel> presenter) {
        deletePermission.execute(param, presenter);
    }
}
