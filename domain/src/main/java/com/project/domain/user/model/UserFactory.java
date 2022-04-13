package com.project.domain.user.model;

import java.sql.Timestamp;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public interface UserFactory {
    User create(int id, String name, String lastname, String dni, String phone,
                String email, String username, String password, int createdBy,
                Timestamp creationDate, boolean status);
}
