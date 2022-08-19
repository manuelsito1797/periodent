package com.project.domain.specialist.model.specialist;

import java.sql.Date;

/**
 * @author dhelarius 5/7/2022
 * periodent
 */
public interface Specialist {

    int getId();

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
