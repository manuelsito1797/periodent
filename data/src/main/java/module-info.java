/**
 * @author dhelarius 10/4/2022
 * periodent
 */module data {

    requires domain;
    requires adapter;
    requires java.sql;

    exports com.project.data.user;
    exports com.project.data.user.permission;
    exports com.project.data.patient;
}