package com.project.data.specialist;

import com.project.domain.gateway.DsGateway;
import com.project.domain.mapper.Mapper;
import com.project.domain.specialist.model.specialist.CommonSpecialist;
import com.project.domain.specialist.model.specialist.SpecialistDsRequestModel;
import com.project.domain.specialist.repository.SpecialistRepository;

import java.util.List;

/**
 * @author dhelarius 10/7/2022
 * periodent
 */
public class SpecialistRepositoryImpl implements SpecialistRepository {

    private final DsGateway<SpecialistDsRequestModel> specialistDsGateway;
    private final SpecialistDao specialistDao;
    private final Mapper converter;

    public SpecialistRepositoryImpl(DsGateway<SpecialistDsRequestModel> specialistDsGateway,
            SpecialistDao specialistDao, Mapper converter) {
        this.specialistDsGateway = specialistDsGateway;
        this.specialistDao = specialistDao;
        this.converter = converter;
    }

    @Override
    public void create(CommonSpecialist specialist) {
        var request = converter.convertEntityToDto(specialist, SpecialistDsRequestModel.class);
        specialistDsGateway.create(request);
        specialistDao.applySpecialties(specialist);
    }

    @Override
    public CommonSpecialist findById(int id) {
        var dsSpecialist = specialistDsGateway.read(id);
        var specialist = converter.convertEntityToDto(dsSpecialist, CommonSpecialist.class);

        // Buscar especialidades del especialista y agregarlas
        var specialties = specialistDao.readAllSpacialtiesBySpecialistId(id);
        specialist.setSpecialties(specialties);

        return specialist;
    }

    @Override
    public List<CommonSpecialist> findAll() {
        var dsSpecialists = specialistDsGateway.readAll();
        var specialists = converter.convertEntitiesToDto(dsSpecialists, CommonSpecialist.class);

        for(var specialist : specialists) {
            var specialties = specialistDao.readAllSpacialtiesBySpecialistId(specialist.getId());
            specialist.setSpecialties(specialties);
        }

        return specialists;
    }

    @Override
    public void update(CommonSpecialist specialist) {
        var request = converter.convertEntityToDto(specialist, SpecialistDsRequestModel.class);
        specialistDsGateway.update(request);
        specialistDao.applySpecialties(specialist);
    }

    @Override
    public void delete(int id) {
        specialistDsGateway.delete(id);
    }

    @Override
    public CommonSpecialist findLast() {
        var dsSpecialist = specialistDsGateway.readLast();
        var specialist = converter.convertEntityToDto(dsSpecialist, CommonSpecialist.class);

        // Buscar especialidades y asignarlas
        var specialties = specialistDao.readAllSpacialtiesBySpecialistId(specialist.getId());
        specialist.setSpecialties(specialties);

        return specialist;
    }
}
