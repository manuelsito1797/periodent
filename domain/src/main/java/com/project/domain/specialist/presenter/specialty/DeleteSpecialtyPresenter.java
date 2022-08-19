package com.project.domain.specialist.presenter.specialty;

import com.project.domain.specialist.interactor.specialty.DeleteSpecialty;
import com.project.domain.specialist.model.specialty.SpecialtyRequestModel;
import com.project.domain.specialist.model.specialty.SpecialtyResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class DeleteSpecialtyPresenter implements
        UseCaseWithParam.Callback<SpecialtyRequestModel, SpecialtyResponseModel> {

    private final DeleteSpecialty deleteSpecialty;

    public DeleteSpecialtyPresenter(DeleteSpecialty deleteSpecialty) {
        this.deleteSpecialty = deleteSpecialty;
    }

    @Override
    public void show(SpecialtyRequestModel param, Presenter<SpecialtyResponseModel> presenter) {
        deleteSpecialty.execute(param, presenter);
    }
}
