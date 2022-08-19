package com.project.domain.patient.presenter;

import com.project.domain.patient.interactor.AddPatient;
import com.project.domain.patient.model.PatientRequestModel;
import com.project.domain.patient.model.PatientResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class AddPatientPresenter implements
        UseCaseWithParam.Callback<PatientRequestModel, PatientResponseModel> {

    private final AddPatient addPatient;

    public AddPatientPresenter(AddPatient addPatient) {
        this.addPatient = addPatient;
    }

    @Override
    public void show(PatientRequestModel param, Presenter<PatientResponseModel> presenter) {
        addPatient.execute(param, presenter);
    }
}
