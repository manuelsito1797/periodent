package com.project.domain.user.interactor;

import com.project.domain.user.model.permission.Permission;
import com.project.domain.user.model.permission.PermissionRequestModel;
import com.project.domain.user.model.permission.PermissionResponseModel;
import com.project.domain.user.repository.PermissionRepository;
import com.project.domain.user.validator.PermissionValidator;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 2/5/2022
 * periodent
 */
public class UpdatePermission implements UseCaseWithParam<PermissionRequestModel, PermissionResponseModel> {

    private final PermissionRepository repository;

    public UpdatePermission(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(PermissionRequestModel param, Presenter<PermissionResponseModel> presenter) {
        try {
            PermissionValidator.validateUpdate(param, repository);

            var permission = new Permission(param.getId(), param.getDescription(),
                    param.getKey(), param.isActive());

            repository.update(permission);

            var permissionById = repository.findById(permission.getId());

            var response = new PermissionResponseModel(permissionById.getId(), permissionById.getDescription(),
                    permissionById.getKey(), permission.isActive());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
