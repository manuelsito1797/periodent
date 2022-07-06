package com.project.domain.patient.interactor;

import com.project.domain.patient.model.FactoryPatient;
import com.project.domain.patient.model.PatienType;
import com.project.domain.patient.model.PatientResponseModel;
import com.project.domain.patient.repository.PatientRepository;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class GetAllPatients implements UseCase<List<PatientResponseModel>> {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public GetAllPatients(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(Presenter<List<PatientResponseModel>> presenter) {
        List<PatientResponseModel> patientResponseModels = new ArrayList<>();
        try {
            var patients = patientRepository.findAll();

            for(var patient : patients) {
                var creator = userRepository.findById(patient.getCreatedBy());
                var creatorName = creator.getName().concat(" " + creator.getLastname());

                var response = (PatientResponseModel) FactoryPatient.createPatient(PatienType.PATIENT_RESPONSE_MODEL, patient.getId(),
                        patient.getName(), patient.getLastname(), patient.getBirthday(), patient.getDni(), patient.getPhone(),
                        patient.getAddress(), patient.getEmail(), patient.getCreatedBy(), creatorName, patient.isActive());

                patientResponseModels.add(response);
            }

            presenter.onResponse(patientResponseModels, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
