package com.project.domain.patient.presenter;

import com.project.domain.patient.interactor.GetAllPatients;
import com.project.domain.patient.model.PatientResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCase;

import java.util.List;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class PatientsPresenter implements UseCase.Callback<List<PatientResponseModel>> {

    private final GetAllPatients getAllPatients;

    public PatientsPresenter(GetAllPatients getAllPatients) {
        this.getAllPatients = getAllPatients;
    }

    @Override
    public void show(Presenter<List<PatientResponseModel>> presenter) {
        getAllPatients.execute(presenter);
    }
}
