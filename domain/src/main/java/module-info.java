/**
 * @author dhelarius 9/4/2022
 * periodent
 */module domain {
    requires java.sql;
    requires java.prefs;

    exports com.project.domain.usecase;
    exports com.project.domain.security;
    exports com.project.domain.gateway;
    exports com.project.domain.view;
    exports com.project.domain.user.model;
    exports com.project.domain.user.model.permission;
    exports com.project.domain.user.repository;
    exports com.project.domain.user.interactor;
    exports com.project.domain.user.presenter;
    exports com.project.domain.user.preferences;
    exports com.project.domain.mapper;
    exports com.project.domain.patient.repository;
    exports com.project.domain.patient.model;
    exports com.project.domain.patient.interactor;
    exports com.project.domain.patient.presenter;
    exports com.project.domain.specialist.repository;
    exports com.project.domain.specialist.model.specialist;
    exports com.project.domain.specialist.model.specialty;
 }