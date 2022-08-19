package com.project.domain.patient.presenter;

import com.project.domain.patient.interactor.DeletePatient;
import com.project.domain.patient.model.PatientRequestModel;
import com.project.domain.patient.model.PatientResponseModel;
import com.project.domain.view.Presenter;
import com.project.domain.view.UseCaseWithParam;

/**
 * @author dhelarius 27/6/2022
 * periodent
 */
public class DeletePatientPresenter
        implements UseCaseWithParam<PatientRequestModel, PatientResponseModel> {

    private final DeletePatient deletePatient;

    public DeletePatientPresenter(DeletePatient deletePatient) {
        this.deletePatient = deletePatient;
    }

    @Override
    public void execute(PatientRequestModel param, Presenter<PatientResponseModel> presenter) {
        deletePatient.execute(param, presenter);
    }
}
