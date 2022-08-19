package com.project.app.patient.viewmodel;

import com.project.app.di.activej.ActiveJ;
import com.project.app.patient.model.FXPatient;
import com.project.app.user.viewmodel.Callback;
import com.project.app.util.DateUtil;
import com.project.domain.patient.model.PatientRequestModel;
import com.project.domain.patient.model.PatientResponseModel;
import com.project.domain.patient.presenter.AddPatientPresenter;
import com.project.domain.patient.presenter.PatientsPresenter;
import com.project.domain.user.preferences.UserPreferences;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author dhelarius 17/7/2022
 * periodent
 */
public class PatientViewModel implements ViewModel {

    private final ObservableList<FXPatient> patients = FXCollections.observableArrayList();

    private final BooleanProperty newPatient = new SimpleBooleanProperty(false);

    public BooleanProperty getNewPatientProperty() {
        return newPatient;
    }

    public void setNewPatient(boolean isNewPatient) {
        newPatient.set(isNewPatient);
    }

    public boolean isNewPatient() {
        return newPatient.get();
    }

    private final ObjectProperty<FXPatient> patientProperty = new SimpleObjectProperty<>();

    public FXPatient getPatient() {
        return patientProperty.get();
    }

    public ObjectProperty<FXPatient> getPatientProperty() {
        return patientProperty;
    }

    public void setPatient(FXPatient patient) {
        this.patientProperty.set(patient);
    }

    public ObservableList<FXPatient> getPatients() {
        return patients;
    }

    public void addPatient(Callback<PatientResponseModel> callback) {
        var presenter = ActiveJ.getInstance(AddPatientPresenter.class);
        var patient = patientProperty.get();

        var request = getPatientRequest(patient);

        presenter.show(request, callback::onPresent);
    }

    public void loadPatients() {
        var presenter = ActiveJ.getInstance(PatientsPresenter.class);
        presenter.show((response, throwable) -> {
            if(throwable != null) {
                System.err.println(throwable.getMessage());
                return;
            }
            for(var patient : response) {
                var localDateStringConverter =
                        DateUtil.getDateStringConverter(patient.getBirthday());
                patients.add(new FXPatient(patient.getId(), patient.getName(), patient.getLastname(), localDateStringConverter,
                        patient.getDni(), patient.getPhone(), patient.getAddress(), patient.getEmail(), patient.isActive()));
            }
        });
    }

    private PatientRequestModel getPatientRequest(FXPatient patient) {
        var pref = UserPreferences.getInstance();
        var userPref = pref.getUserFromPreference();

        var birthday = DateUtil.getDateFromStr(patient.getBirthday().toString());
        return new PatientRequestModel(patient.getId(), patient.getName(), patient.getLastname(), birthday,
                patient.getDni(), patient.getPhone(), patient.getAddress(), patient.getEmail(), userPref.getId(), patient.isActive());
    }
}
