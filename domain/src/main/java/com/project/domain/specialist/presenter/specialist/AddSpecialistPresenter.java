package com.project.domain.specialist.presenter.specialist;

import com.project.domain.specialist.interactor.specialist.AddSpecialist;
import com.project.domain.specialist.model.specialist.SpecialistRequestModel;
import com.project.domain.specialist.model.specialist.SpecialistResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class AddSpecialistPresenter implements
        UseCaseWithParam.Callback<SpecialistRequestModel, SpecialistResponseModel> {

    private final AddSpecialist addSpecialist;

    public AddSpecialistPresenter(AddSpecialist addSpecialist) {
        this.addSpecialist = addSpecialist;
    }

    @Override
    public void show(SpecialistRequestModel param, Presenter<SpecialistResponseModel> presenter) {
        addSpecialist.execute(param, presenter);
    }
}
