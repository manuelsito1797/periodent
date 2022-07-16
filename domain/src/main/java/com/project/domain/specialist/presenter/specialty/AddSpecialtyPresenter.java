package com.project.domain.specialist.presenter.specialty;

import com.project.domain.specialist.interactor.specialty.AddSpecialty;
import com.project.domain.specialist.model.specialty.SpecialtyRequestModel;
import com.project.domain.specialist.model.specialty.SpecialtyResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class AddSpecialtyPresenter implements
        UseCaseWithParam<SpecialtyRequestModel, SpecialtyResponseModel> {

    private final AddSpecialty addSpecialty;

    public AddSpecialtyPresenter(AddSpecialty addSpecialty) {
        this.addSpecialty = addSpecialty;
    }

    @Override
    public void execute(SpecialtyRequestModel param, Presenter<SpecialtyResponseModel> presenter) {
        addSpecialty.execute(param, presenter);
    }
}
