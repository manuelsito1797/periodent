package com.project.domain.specialist.repository;

import com.project.domain.specialist.model.specialist.CommonSpecialist;

import java.util.List;

/**
 * @author dhelarius 7/7/2022
 * periodent
 */
public interface SpecialistRepository {

    void create(CommonSpecialist specialist);

    CommonSpecialist findById(int id);

    List<CommonSpecialist> findAll();

    void update(CommonSpecialist specialist);

    void delete(int id);

    CommonSpecialist findLast();
}
