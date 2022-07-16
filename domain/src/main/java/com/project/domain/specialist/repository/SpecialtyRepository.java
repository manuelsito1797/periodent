package com.project.domain.specialist.repository;

import com.project.domain.specialist.model.specialty.CommonSpecialty;

import java.util.List;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public interface SpecialtyRepository {

    void create(CommonSpecialty specialty);

    CommonSpecialty findById(int id);

    List<CommonSpecialty> findAll();

    void update(CommonSpecialty specialty);

    void delete(int id);

    CommonSpecialty findLast();
}
