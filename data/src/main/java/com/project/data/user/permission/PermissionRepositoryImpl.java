package com.project.data.user.permission;

import com.project.adapter.converter.modelmapper.EntityDtoConverter;
import com.project.domain.user.model.permission.Permission;
import com.project.domain.user.model.permission.PermissionDsRequestModel;
import com.project.domain.user.repository.PermissionRepository;
import com.project.domain.gateway.DsGateway;

import java.util.List;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public class PermissionRepositoryImpl implements PermissionRepository {

    private final DsGateway<PermissionDsRequestModel> dsPermission;
    private final EntityDtoConverter converter;

    public PermissionRepositoryImpl(DsGateway<PermissionDsRequestModel> dsPermission,
                                    EntityDtoConverter converter) {
        this.dsPermission = dsPermission;
        this.converter = converter;
    }

    @Override
    public void create(Permission permission) {
        var request = converter
                .convertEntityToDto(permission, PermissionDsRequestModel.class);
        dsPermission.create(request);
    }

    @Override
    public Permission findById(int id) {
        var permissionFromDS = dsPermission.read(id);
        return converter.convertEntityToDto(permissionFromDS, Permission.class);
    }

    @Override
    public Permission findByDescription(String description) {
        var permissions = findAll();
        for(var permission : permissions) {
            if(permission.getDescription().equalsIgnoreCase(description)) return permission;
        }
        return null;
    }

    @Override
    public Permission findByKey(String key) {
        var permissions = findAll();
        for(var permission : permissions) {
            if(permission.getKey().equalsIgnoreCase(key)) return permission;
        }
        return null;
    }

    @Override
    public List<Permission> findAll() {
        var permissionsFromDS = dsPermission.readAll();
        return converter.convertEntitiesToDto(permissionsFromDS, Permission.class);
    }

    @Override
    public void update(Permission permission) {
        var request = converter
                .convertEntityToDto(permission, PermissionDsRequestModel.class);
        dsPermission.update(request);
    }

    @Override
    public void delete(int id) {

    }
}
