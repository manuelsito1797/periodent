package com.project.domain.specialist.presenter.specialist;

import com.project.domain.specialist.interactor.specialist.GetAllSpecialists;
import com.project.domain.specialist.model.specialist.SpecialistResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCase;

import java.util.List;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class GetAllSpecialitsPresenter implements UseCase.Callback<List<SpecialistResponseModel>> {

    private final GetAllSpecialists getAllSpecialists;

    public GetAllSpecialitsPresenter(GetAllSpecialists getAllSpecialists) {
        this.getAllSpecialists = getAllSpecialists;
    }

    @Override
    public void show(Presenter<List<SpecialistResponseModel>> presenter) {
        getAllSpecialists.execute(presenter);
    }
}
