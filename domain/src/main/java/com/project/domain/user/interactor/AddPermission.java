package com.project.domain.user.interactor;

import com.project.domain.user.model.permission.Permission;
import com.project.domain.user.model.permission.PermissionRequestModel;
import com.project.domain.user.model.permission.PermissionResponseModel;
import com.project.domain.user.repository.PermissionRepository;
import com.project.domain.user.validator.PermissionValidator;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 30/4/2022
 * periodent
 */
public class AddPermission implements UseCaseWithParam<PermissionRequestModel, PermissionResponseModel> {

    private final PermissionRepository repository;

    public AddPermission(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(PermissionRequestModel param,
                        Presenter<PermissionResponseModel> presenter) {
        try {
            PermissionValidator.validate(param, repository);

            var permission = new Permission(param.getId(), param.getDescription(),
                    param.getKey(), param.isActive());

            repository.create(permission);

            var permissionByKey = repository.findByKey(permission.getKey());

            var response = new PermissionResponseModel(permissionByKey.getId(), permissionByKey.getDescription(),
                    permissionByKey.getKey(), permissionByKey.isActive());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
