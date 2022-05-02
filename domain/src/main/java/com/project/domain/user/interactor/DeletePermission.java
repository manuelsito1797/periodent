package com.project.domain.user.interactor;

import com.project.domain.user.model.permission.Permission;
import com.project.domain.user.model.permission.PermissionRequestModel;
import com.project.domain.user.model.permission.PermissionResponseModel;
import com.project.domain.user.repository.PermissionRepository;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.user.validator.PermissionValidator;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 2/5/2022
 * periodent
 */
public class DeletePermission implements UseCaseWithParam<PermissionRequestModel, PermissionResponseModel> {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    public DeletePermission(UserRepository userRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void execute(PermissionRequestModel param, Presenter<PermissionResponseModel> presenter) {
        try {
            PermissionValidator.validateDelete(param, userRepository);

            var permission = new Permission(param.getId(), param.getDescription(),
                    param.getKey(), param.isActive());

            permissionRepository.delete(permission);

            var response = new PermissionResponseModel(permission.getId(), permission.getDescription(),
                    permission.getKey(), permission.isActive());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
