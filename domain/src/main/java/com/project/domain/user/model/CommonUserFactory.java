package com.project.domain.user.model;

import java.sql.Timestamp;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class CommonUserFactory  implements UserFactory {
    @Override
    public User create(int id, String name, String lastname, String dni, String phone, String email,
                       String username, String password, int createdBy, Timestamp creationDate,
                       boolean status) {
        return new CommonUser(id, name, lastname, dni, phone, email,
                username, password, createdBy, creationDate, status);
    }
}
