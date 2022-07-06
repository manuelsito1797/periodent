package com.project.domain.patient.repository;

import com.project.domain.patient.model.CommonPatient;

import java.util.List;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public interface PatientRepository {

    void create(CommonPatient patient);

    CommonPatient findById(int id);

    List<CommonPatient> findAll();

    void Update(CommonPatient patient);

    void delete(int id);

    CommonPatient findLast();
}
