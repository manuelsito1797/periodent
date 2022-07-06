package com.project.domain.patient.model;

import java.sql.Date;

/**
 * @author dhelarius 19/6/2022
 * periodent
 */
interface Patient {

    String getName();

    String getLastname();

    Date getBirthday();

    String getDni();

    String getPhone();

    String getAddress();

    String getEmail();

    int getCreatedBy();

    boolean isActive();
}
