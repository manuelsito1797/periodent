package com.project.domain.user.repository;

import com.project.domain.user.model.CommonUser;
import com.project.domain.user.model.permission.UserPermission;

import java.util.List;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public interface UserRepository {

    void create(CommonUser user);

    CommonUser findById(int id);

    CommonUser findByUsername(String username);

    List<CommonUser> findAll();

    void update(CommonUser user);

    void delete(int id);

    boolean existByUsername(String username);
}
