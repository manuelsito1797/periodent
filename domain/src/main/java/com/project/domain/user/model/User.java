package com.project.domain.user.model;

import java.sql.Timestamp;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public interface User {

    int getId();

    String getName();

    String getLastname();

    String getDni();

    String getPhone();

    String getEmail();

    String getUsername();

    String getPassword();

    int getCreatedBy();

    Timestamp getCreationDate();

    boolean isStatus();
}
