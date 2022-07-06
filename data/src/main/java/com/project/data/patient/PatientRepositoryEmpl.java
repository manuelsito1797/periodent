package com.project.data.patient;

import com.project.domain.gateway.DsGateway;
import com.project.domain.mapper.Mapper;
import com.project.domain.patient.model.CommonPatient;
import com.project.domain.patient.model.PatientDsRequestModel;
import com.project.domain.patient.repository.PatientRepository;

import java.util.List;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class PatientRepositoryEmpl implements PatientRepository {

    private final DsGateway<PatientDsRequestModel> patientDsGateway;
    private final Mapper converter;

    public PatientRepositoryEmpl(DsGateway<PatientDsRequestModel> patientDsGateway, Mapper converter) {
        this.patientDsGateway = patientDsGateway;
        this.converter = converter;
    }

    @Override
    public void create(CommonPatient patient) {
        var request = converter.convertEntityToDto(patient, PatientDsRequestModel.class);
        patientDsGateway.create(request);
    }

    @Override
    public CommonPatient findById(int id) {
        var patient = patientDsGateway.read(id);
        return converter.convertEntityToDto(patient, CommonPatient.class);
    }

    @Override
    public List<CommonPatient> findAll() {
        var patients = patientDsGateway.readAll();
        return converter.convertEntitiesToDto(patients, CommonPatient.class);
    }

    @Override
    public void Update(CommonPatient patient) {
        var dsPatient = converter.convertEntityToDto(patient, PatientDsRequestModel.class);
        patientDsGateway.update(dsPatient);
    }

    @Override
    public void delete(int id) {
        patientDsGateway.delete(id);
    }

    @Override
    public CommonPatient findLast() {
        var patient = patientDsGateway.readLast();
        return converter.convertEntityToDto(patient, CommonPatient.class);
    }
}
