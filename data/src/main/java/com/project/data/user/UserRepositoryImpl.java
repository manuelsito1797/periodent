package com.project.data.user;

import com.project.adapter.converter.modelmapper.EntityDtoConverter;
import com.project.data.user.permission.DSPermission;
import com.project.domain.gateway.DsGateway;
import com.project.domain.user.model.CommonUser;
import com.project.domain.user.model.UserDsRequestModel;
import com.project.domain.user.model.permission.Permission;
import com.project.domain.user.repository.UserRepository;

import java.util.List;

/**
 * @author dhelarius 11/4/2022
 * periodent
 */
public class UserRepositoryImpl implements UserRepository {

    private final DsGateway<UserDsRequestModel> userDsGateway;
    private final DSPermission permissionDsGateway;
    private final EntityDtoConverter converter;

    public UserRepositoryImpl(DsGateway<UserDsRequestModel> userDsGateway,
                              DSPermission permissionDsGateway,
                              EntityDtoConverter converter) {
        this.userDsGateway = userDsGateway;
        this.permissionDsGateway = permissionDsGateway;
        this.converter = converter;
    }

    @Override
    public void create(CommonUser user) {

    }

    @Override
    public CommonUser findById(int id) {
        var dsUser = userDsGateway.read(id);

        var user = converter.convertEntityToDto(dsUser, CommonUser.class);
        var permissions = getPermissions(user.getId());
        user.setPermissions(permissions);

        return user;
    }

    @Override
    public CommonUser findByUsername(String username) {
        var users = findAll();

        if(users.isEmpty()) return null;

        for(var user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<CommonUser> findAll() {
        var dsUsers = userDsGateway.readAll();

        var users = converter.convertEntitiesToDto(dsUsers, CommonUser.class);
        for(var user: users) {
            var permissions = getPermissions(user.getId());
            user.setPermissions(permissions);
        }
        return users;
    }

    @Override
    public void update(CommonUser user) {

    }

    @Override
    public void delete(int id) {

    }

    private List<Permission> getPermissions(int id) {
        var permissionsFromDS = permissionDsGateway.readAllByUserId(id);
        return converter.convertEntitiesToDto(permissionsFromDS, Permission.class);
    }
}
