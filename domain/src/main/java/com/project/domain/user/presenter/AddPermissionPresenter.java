package com.project.domain.user.presenter;

import com.project.domain.user.interactor.AddPermission;
import com.project.domain.user.model.permission.PermissionRequestModel;
import com.project.domain.user.model.permission.PermissionResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 30/4/2022
 * periodent
 */
public class AddPermissionPresenter implements
        UseCaseWithParam.Callback<PermissionRequestModel, PermissionResponseModel> {

    private final AddPermission addPermission;

    public AddPermissionPresenter(AddPermission addPermission) {
        this.addPermission = addPermission;
    }

    @Override
    public void show(PermissionRequestModel param, Presenter<PermissionResponseModel> presenter) {
        addPermission.execute(param, presenter);
    }
}
