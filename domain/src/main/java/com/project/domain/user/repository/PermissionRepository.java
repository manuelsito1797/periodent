package com.project.domain.user.repository;

import com.project.domain.user.model.permission.Permission;

import java.util.List;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public interface PermissionRepository {

    void create(Permission permission);

    Permission findById(int id);

    Permission findByDescription(String description);

    Permission findByKey(String key);

    List<Permission> findAll();

    void update(Permission permission);

    void delete(Permission permission);
}
