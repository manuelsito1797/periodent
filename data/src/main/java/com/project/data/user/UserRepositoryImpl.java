package com.project.data.user;

import com.project.adapter.converter.modelmapper.EntityDtoConverter;
import com.project.domain.gateway.DsGateway;
import com.project.domain.user.model.CommonUser;
import com.project.domain.user.model.UserDsRequestModel;
import com.project.domain.user.repository.UserRepository;

import java.util.List;

/**
 * @author dhelarius 11/4/2022
 * periodent
 */
public class UserRepositoryImpl implements UserRepository {

    private final DsGateway<UserDsRequestModel> userDsGateway;
    private final EntityDtoConverter converter;

    public UserRepositoryImpl(DsGateway<UserDsRequestModel> userDsGateway,
                              EntityDtoConverter converter) {
        this.userDsGateway = userDsGateway;
        this.converter = converter;
    }

    @Override
    public void create(CommonUser user) {

    }

    @Override
    public CommonUser findById(int id) {
        return null;
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
        return converter.convertEntitiesToDto(dsUsers, CommonUser.class);
    }

    @Override
    public void update(CommonUser user) {

    }

    @Override
    public void delete(int id) {

    }
}
