package com.project.domain.patient.interactor;

import com.project.domain.mapper.Mapper;
import com.project.domain.patient.model.*;
import com.project.domain.patient.repository.PatientRepository;
import com.project.domain.patient.validator.PatientValidator;
import com.project.domain.user.model.CommonUser;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 27/6/2022
 * periodent
 */
public class UpdatePatient implements UseCaseWithParam<PatientRequestModel, PatientResponseModel> {

    private final PatientRepository repository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public UpdatePatient(PatientRepository repository, UserRepository userRepository,
                         Mapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void execute(PatientRequestModel param, Presenter<PatientResponseModel> presenter) {
        try {
            PatientValidator.validate(param);

            var patient = mapper.convertEntityToDto(param, CommonPatient.class);

            repository.Update(patient);

            var updated = repository.findById(param.getId());
            var creator = userRepository.findById(updated.getCreatedBy());
            var creatorName = creator.getName().concat(" " + creator.getLastname());

            var response = (PatientResponseModel) FactoryPatient.createPatient(PatienType.PATIENT_RESPONSE_MODEL, updated.getId(),
                    updated.getName(), updated.getLastname(), updated.getBirthday(), updated.getDni(), updated.getPhone(),
                    updated.getAddress(), updated.getEmail(), updated.getCreatedBy(), creatorName, updated.isActive());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
