package com.project.data.user;

import com.project.adapter.converter.modelmapper.EntityDtoConverter;
import com.project.data.user.permission.UserPermissionDao;
import com.project.domain.gateway.DsGateway;
import com.project.domain.mapper.Mapper;
import com.project.domain.user.model.CommonUser;
import com.project.domain.user.model.UserDsRequestModel;
import com.project.domain.user.model.permission.UserPermission;
import com.project.domain.user.repository.UserRepository;

import java.util.List;

/**
 * @author dhelarius 11/4/2022
 * periodent
 */
public class UserRepositoryImpl implements UserRepository {

    private final DsGateway<UserDsRequestModel> userDsGateway;
    private final UserDao userDao;
    private final UserPermissionDao userPermissionDao;
    private final Mapper converter;

    public UserRepositoryImpl(DsGateway<UserDsRequestModel> userDsGateway,
                              UserDao userDao,
                              UserPermissionDao userPermissionDao,
                              Mapper converter) {
        this.userDsGateway = userDsGateway;
        this.userDao = userDao;
        this.userPermissionDao = userPermissionDao;
        this.converter = converter;
    }

    @Override
    public void create(CommonUser user) {
        var userFromDs = converter.convertEntityToDto(user, UserDsRequestModel.class);
        userDsGateway.create(userFromDs);
        userDao.applyUserPermissions(user);
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
                var permissions = getPermissions(user.getId());
                user.setPermissions(permissions);
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
        var userRequest = converter.convertEntityToDto(user, UserDsRequestModel.class);
        userDsGateway.update(userRequest);
        userDao.applyUserPermissions(user);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean existByUsername(String username) {
        var user = findByUsername(username);
        return user != null;
    }

    private List<UserPermission> getPermissions(int id) {
        var permissionsFromDS = userPermissionDao.readAllByUserId(id);
        return converter.convertEntitiesToDto(permissionsFromDS, UserPermission.class);
    }
}
