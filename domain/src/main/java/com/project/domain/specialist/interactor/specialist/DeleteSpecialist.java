package com.project.domain.specialist.interactor.specialist;

import com.project.domain.mapper.Mapper;
import com.project.domain.specialist.model.specialist.CommonSpecialist;
import com.project.domain.specialist.model.specialist.SpecialistRequestModel;
import com.project.domain.specialist.model.specialist.SpecialistResponseModel;
import com.project.domain.specialist.repository.SpecialistRepository;
import com.project.domain.specialist.validator.SpecialistValidator;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class DeleteSpecialist implements UseCaseWithParam<SpecialistRequestModel, SpecialistResponseModel> {

    private final SpecialistRepository specialistRepository;
    private final UserRepository userRepository;
    private final Mapper converter;

    public DeleteSpecialist(SpecialistRepository specialistRepository, UserRepository userRepository,
                            Mapper converter) {
        this.specialistRepository = specialistRepository;
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @Override
    public void execute(SpecialistRequestModel param, Presenter<SpecialistResponseModel> presenter) {
        try {
            SpecialistValidator.validateDelete(param, specialistRepository);

            var specialist = converter.convertEntityToDto(param, CommonSpecialist.class);

            specialistRepository.delete(specialist.getId());

            var creator = userRepository.findById(specialist.getCreatedBy());
            var creatorName = creator.getName().concat(" " + creator.getLastname());

            var response = new SpecialistResponseModel(specialist.getId(), specialist.getName(), specialist.getLastname(),
                    specialist.getBirthday(), specialist.getDni(), specialist.getPhone(), specialist.getAddress(), specialist.getEmail(), specialist.getCreatedBy(),
                    creatorName, specialist.isActive(), specialist.getSpecialties());

            presenter.onResponse(response, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
