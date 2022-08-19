package com.project.domain.specialist.interactor.specialist;

import com.project.domain.mapper.Mapper;
import com.project.domain.specialist.validator.SpecialistValidator;
import com.project.domain.specialist.model.specialist.CommonSpecialist;
import com.project.domain.specialist.model.specialist.SpecialistRequestModel;
import com.project.domain.specialist.model.specialist.SpecialistResponseModel;
import com.project.domain.specialist.repository.SpecialistRepository;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 7/7/2022
 * periodent
 */
public class AddSpecialist implements UseCaseWithParam<SpecialistRequestModel, SpecialistResponseModel> {

    private final SpecialistRepository specialistRepository;
    private final UserRepository userRepository;
    private final Mapper converter;

    public AddSpecialist(SpecialistRepository specialistRepository, UserRepository userRepository,
                         Mapper converter) {
        this.specialistRepository = specialistRepository;
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @Override
    public void execute(SpecialistRequestModel param, Presenter<SpecialistResponseModel> presenter) {
        try{
            SpecialistValidator.validate(param);

            var specialist = converter.convertEntityToDto(param, CommonSpecialist.class);

            specialistRepository.create(specialist);

            var last = specialistRepository.findLast();
            var creator = userRepository.findById(last.getCreatedBy());
            var creatorName = creator.getName().concat(" " + creator.getLastname());

            var response = new SpecialistResponseModel(last.getId(), last.getName(), last.getLastname(),
                    last.getBirthday(), last.getDni(), last.getPhone(), last.getAddress(), last.getEmail(), last.getCreatedBy(), creatorName,
                    last.isActive(), last.getSpecialties());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
