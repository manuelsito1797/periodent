package com.project.domain.specialist.presenter.specialty;

import com.project.domain.specialist.interactor.specialty.GetAllSpecialty;
import com.project.domain.specialist.model.specialty.SpecialtyResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCase;

import java.util.List;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class GetAllSpecialtyPresenter implements
        UseCase.Callback<List<SpecialtyResponseModel>> {

    private final GetAllSpecialty getAllSpecialty;

    public GetAllSpecialtyPresenter(GetAllSpecialty getAllSpecialty) {
        this.getAllSpecialty = getAllSpecialty;
    }

    @Override
    public void show(Presenter<List<SpecialtyResponseModel>> presenter) {
        getAllSpecialty.execute(presenter);
    }
}
