package com.project.data.specialist.specialty;

import com.project.domain.gateway.DsGateway;
import com.project.domain.mapper.Mapper;
import com.project.domain.specialist.model.specialty.CommonSpecialty;
import com.project.domain.specialist.model.specialty.SpecialtyDsRequestModel;
import com.project.domain.specialist.repository.SpecialtyRepository;

import java.util.List;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class SpecialtyRepositoryImpl implements SpecialtyRepository {

    private final DsGateway<SpecialtyDsRequestModel> specialtyDsGateway;
    private final Mapper converter;

    public SpecialtyRepositoryImpl(DsGateway<SpecialtyDsRequestModel> specialtyDsGateway, Mapper converter) {
        this.specialtyDsGateway = specialtyDsGateway;
        this.converter = converter;
    }

    @Override
    public void create(CommonSpecialty specialty) {
        var request = converter.convertEntityToDto(specialty, SpecialtyDsRequestModel.class);
        specialtyDsGateway.create(request);
    }

    @Override
    public CommonSpecialty findById(int id) {
        var specialty = specialtyDsGateway.read(id);
        return converter.convertEntityToDto(specialty, CommonSpecialty.class);
    }

    @Override
    public List<CommonSpecialty> findAll() {
        var specialties = specialtyDsGateway.readAll();
        return converter.convertEntitiesToDto(specialties, CommonSpecialty.class);
    }

    @Override
    public void update(CommonSpecialty specialty) {
        var request =
                converter.convertEntityToDto(specialty, SpecialtyDsRequestModel.class);
        specialtyDsGateway.update(request);
    }

    @Override
    public void delete(int id) {
        specialtyDsGateway.delete(id);
    }

    @Override
    public CommonSpecialty findLast() {
        var specialty = specialtyDsGateway.readLast();
        return converter.convertEntityToDto(specialty, CommonSpecialty.class);
    }
}
