package com.project.domain.patient.presenter;

import com.project.domain.patient.interactor.UpdatePatient;
import com.project.domain.patient.model.PatientRequestModel;
import com.project.domain.patient.model.PatientResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 27/6/2022
 * periodent
 */
public class UpdatePatientPresenter implements
        UseCaseWithParam.Callback<PatientRequestModel, PatientResponseModel> {

    private final UpdatePatient updatePatient;

    public UpdatePatientPresenter(UpdatePatient updatePatient) {
        this.updatePatient = updatePatient;
    }

    @Override
    public void show(PatientRequestModel param, Presenter<PatientResponseModel> presenter) {
        updatePatient.execute(param, presenter);
    }
}
