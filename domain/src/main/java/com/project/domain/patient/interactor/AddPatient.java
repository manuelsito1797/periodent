package com.project.domain.patient.interactor;

import com.project.domain.patient.model.*;
import com.project.domain.patient.repository.PatientRepository;
import com.project.domain.patient.validator.PatientValidator;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class AddPatient implements UseCaseWithParam<PatientRequestModel, PatientResponseModel> {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public AddPatient(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(PatientRequestModel param, Presenter<PatientResponseModel> presenter) {
        try {
            PatientValidator.validate(param);

            var patient = new CommonPatient(); /*(CommonPatient) FactoryPatient.createPatient(PatienType.COMMON_PATIENT,0, param.getName(),
                    param.getLastname(), param.getBirthday(), param.getDni(), param.getPhone(), param.getAddress(), param.getEmail(),
                    param.getCreatedBy(), null, param.isActive());*/

            patientRepository.create(patient);

            var last = patientRepository.findLast();
            var creator = userRepository.findById(last.getCreatedBy());
            var creatorName = creator.getName().concat(" " + creator.getLastname());

            var response = new PatientResponseModel(); /*(PatientResponseModel) FactoryPatient.createPatient(PatienType.PATIENT_RESPONSE_MODEL,
                    last.getId(), last.getName(), last.getLastname(), last.getBirthday(), last.getDni(), last.getPhone(),
                    last.getAddress(), last.getEmail(), last.getCreatedBy(), creatorName, last.isActive());*/

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
