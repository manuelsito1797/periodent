package com.project.app.patient.view;

import com.project.app.patient.model.FXPatient;
import com.project.app.patient.viewmodel.PatientViewModel;
import com.project.app.util.DateUtil;
import com.project.app.util.DialogUtil;
import com.project.app.util.StageEventUtil;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author dhelarius 21/7/2022
 * periodent
 */
public class EditPatientView implements FxmlView<PatientViewModel>, Initializable {

    @InjectViewModel
    private PatientViewModel viewModel;

    // Fields
    @FXML private TextField nameField;
    @FXML private TextField lastnameField;
    @FXML private DatePicker birthdayField;
    @FXML private TextField emailField;
    @FXML private TextField dniField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        handleStageEvents(stage);
    }

    private void handleStageEvents(Stage stage) {
        StageEventUtil.setOnCloseRequest(this::onCancel, stage);
    }

    private int onCancel() {
        viewModel.getPatients().removeAll(viewModel.getPatients());
        viewModel.loadPatients();
        return 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var patient = viewModel.getPatient();
        nameField.textProperty().bindBidirectional(patient.nameProperty());
        lastnameField.textProperty().bindBidirectional(patient.lastnameProperty());
        birthdayField.converterProperty().bindBidirectional(patient.birthdayProperty());
        dniField.textProperty().bindBidirectional(patient.dniProperty());
        phoneField.textProperty().bindBidirectional(patient.phoneProperty());
        addressField.textProperty().bindBidirectional(patient.addressProperty());
    }

    @FXML
    public void handleSave() {
        if(viewModel.isNewPatient()) {
            DialogUtil.showConfirmationMessage(
                    "¿Desea guardar el nuevo paciente?", "", this::addPatient
            );
        } /*else {
            DialogUtil.showConfirmationMessage(
                    "¿Desea guardar los cambios realizados en el permiso?", "", this::updatePermission
            );
        }*/
    }

    private void addPatient() {
        viewModel.addPatient((response, throwable) -> {
            if(throwable != null) {
                DialogUtil.showErrorMessage(throwable, "Error al guardar el paciente");
                return;
            }

            viewModel.getPatients().add(new FXPatient(response.getId(),
                    response.getName(), response.getLastname(), DateUtil.getDateStringConverter(response.getBirthday()),
                    response.getDni(), response.getPhone(), response.getAddress(), response.getEmail(), response.isActive()));

            nameField.clear();
            lastnameField.clear();
            dniField.clear();
            phoneField.clear();
            addressField.clear();
            emailField.clear();

            DialogUtil.showMessage("Nuevo Paciente", "¡Se ha creado el paciente satisfactoriamente!");
        });
    }

    @FXML
    public void handleClose() {
        stage.close();
    }
}
