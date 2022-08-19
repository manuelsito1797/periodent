package com.project.domain.specialist.interactor.specialty;

import com.project.domain.mapper.Mapper;
import com.project.domain.specialist.model.specialty.CommonSpecialty;
import com.project.domain.specialist.model.specialty.SpecialtyRequestModel;
import com.project.domain.specialist.model.specialty.SpecialtyResponseModel;
import com.project.domain.specialist.repository.SpecialtyRepository;
import com.project.domain.specialist.validator.SpecialtyValidator;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class AddSpecialty implements UseCaseWithParam<SpecialtyRequestModel, SpecialtyResponseModel> {

    private final SpecialtyRepository specialtyRepository;
    private final UserRepository userRepository;
    private final Mapper converter;

    public AddSpecialty(SpecialtyRepository specialtyRepository, UserRepository userRepository,
                        Mapper converter) {
        this.specialtyRepository = specialtyRepository;
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @Override
    public void execute(SpecialtyRequestModel param, Presenter<SpecialtyResponseModel> presenter) {
        try {
            SpecialtyValidator.validate(param);

            var specialty = converter.convertEntityToDto(param, CommonSpecialty.class);

            specialtyRepository.create(specialty);

            var last = specialtyRepository.findLast();
            var response = new SpecialtyResponseModel(last.getId(), last.getDescription(), last.isActive());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
