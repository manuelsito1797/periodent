package com.project.domain.patient.interactor;

import com.project.domain.mapper.Mapper;
import com.project.domain.patient.model.*;
import com.project.domain.patient.repository.PatientRepository;
import com.project.domain.patient.validator.PatientValidator;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 27/6/2022
 * periodent
 */
public class DeletePatient implements UseCaseWithParam<PatientRequestModel, PatientResponseModel> {

    private final PatientRepository repository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public DeletePatient(PatientRepository repository, UserRepository userRepository,
                         Mapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void execute(PatientRequestModel param, Presenter<PatientResponseModel> presenter) {
        try {
            PatientValidator.validateDelete(param, repository);

            var patient = mapper.convertEntityToDto(param, CommonPatient.class);

            repository.delete(patient.getId());

            var creator = userRepository.findById(patient.getCreatedBy());
            var creatorName = creator.getName().concat(" " + creator.getLastname());

            var response = (PatientResponseModel) FactoryPatient.createPatient(PatienType.PATIENT_RESPONSE_MODEL, patient.getId(),
                    patient.getName(), patient.getLastname(), patient.getBirthday(), patient.getDni(), patient.getPhone(),
                    patient.getAddress(), patient.getEmail(), patient.getCreatedBy(), creatorName, patient.isActive());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
