package com.project.domain.specialist.interactor.specialty;

import com.project.domain.mapper.Mapper;
import com.project.domain.specialist.model.specialty.CommonSpecialty;
import com.project.domain.specialist.model.specialty.SpecialtyRequestModel;
import com.project.domain.specialist.model.specialty.SpecialtyResponseModel;
import com.project.domain.specialist.repository.SpecialtyRepository;
import com.project.domain.specialist.validator.SpecialtyValidator;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class UpdateSpecialty implements UseCaseWithParam<SpecialtyRequestModel, SpecialtyResponseModel> {

    private final SpecialtyRepository repository;
    private final Mapper converter;

    public UpdateSpecialty(SpecialtyRepository repository, Mapper converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public void execute(SpecialtyRequestModel param, Presenter<SpecialtyResponseModel> presenter) {
        try {
            SpecialtyValidator.validate(param);

            var specialty = converter.convertEntityToDto(param, CommonSpecialty.class);

            repository.update(specialty);

            var update = repository.findById(specialty.getId());
            var response = new SpecialtyResponseModel(update.getId(), update.getDescription(), update.isActive());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
