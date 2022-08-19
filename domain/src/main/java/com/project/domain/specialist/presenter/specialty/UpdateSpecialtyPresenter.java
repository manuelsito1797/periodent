package com.project.domain.specialist.presenter.specialty;

import com.project.domain.specialist.interactor.specialty.UpdateSpecialty;
import com.project.domain.specialist.model.specialty.SpecialtyRequestModel;
import com.project.domain.specialist.model.specialty.SpecialtyResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class UpdateSpecialtyPresenter implements
        UseCaseWithParam.Callback<SpecialtyRequestModel, SpecialtyResponseModel> {

    private final UpdateSpecialty updateSpecialty;

    public UpdateSpecialtyPresenter(UpdateSpecialty updateSpecialty) {
        this.updateSpecialty = updateSpecialty;
    }

    @Override
    public void show(SpecialtyRequestModel param, Presenter<SpecialtyResponseModel> presenter) {
        updateSpecialty.execute(param, presenter);
    }
}
