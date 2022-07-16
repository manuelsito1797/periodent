package com.project.domain.specialist.interactor.specialist;

import com.project.domain.specialist.model.specialist.SpecialistResponseModel;
import com.project.domain.specialist.repository.SpecialistRepository;
import com.project.domain.user.repository.UserRepository;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class GetAllSpecialists implements UseCase<List<SpecialistResponseModel>> {

    private final SpecialistRepository specialistRepository;
    private final UserRepository userRepository;

    public GetAllSpecialists(SpecialistRepository specialistRepository, UserRepository userRepository) {
        this.specialistRepository = specialistRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(Presenter<List<SpecialistResponseModel>> presenter) {
        List<SpecialistResponseModel> responses = new ArrayList<>();
        try {
            var specialists = specialistRepository.findAll();

            for(var specialist : specialists) {
                var creator = userRepository.findById(specialist.getCreatedBy());
                var creatorName = creator.getName().concat(" " + creator.getLastname());

                var response = new SpecialistResponseModel(specialist.getId(), specialist.getName(), specialist.getName(),
                        specialist.getBirthday(), specialist.getDni(), specialist.getPhone(), specialist.getAddress(), specialist.getEmail(),
                        specialist.getCreatedBy(), creatorName, specialist.isActive(), specialist.getSpecialties());

                responses.add(response);
            }

            presenter.onResponse(responses, null);
        } catch (Throwable throwable) {
            presenter.onResponse(null, throwable);
        }
    }
}
