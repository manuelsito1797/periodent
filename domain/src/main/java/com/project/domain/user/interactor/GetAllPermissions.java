package com.project.domain.user.interactor;

import com.project.domain.user.model.permission.PermissionResponseModel;
import com.project.domain.user.repository.PermissionRepository;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public class GetAllPermissions implements UseCase<List<PermissionResponseModel>> {

    private final PermissionRepository repository;

    public GetAllPermissions(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Presenter<List<PermissionResponseModel>> presenter) {
        try {
            var permissions = repository.findAll();

            List<PermissionResponseModel> response = new ArrayList<>();
            for(var permission : permissions) {
                response.add(new PermissionResponseModel(permission.getId(), permission.getDescription(),
                        permission.getKey(), permission.isActive()));
            }
            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
