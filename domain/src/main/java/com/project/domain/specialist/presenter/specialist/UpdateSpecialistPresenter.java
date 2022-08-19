package com.project.domain.specialist.presenter.specialist;

import com.project.domain.specialist.interactor.specialist.UpdateSpecialist;
import com.project.domain.specialist.model.specialist.SpecialistRequestModel;
import com.project.domain.specialist.model.specialist.SpecialistResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class UpdateSpecialistPresenter implements
        UseCaseWithParam<SpecialistRequestModel, SpecialistResponseModel> {

    private final UpdateSpecialist updateSpecialist;

    public UpdateSpecialistPresenter(UpdateSpecialist updateSpecialist) {
        this.updateSpecialist = updateSpecialist;
    }

    @Override
    public void execute(SpecialistRequestModel param, Presenter<SpecialistResponseModel> presenter) {
        updateSpecialist.execute(param, presenter);
    }
}
