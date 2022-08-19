package com.project.app.patient.view;

import com.project.app.PeriodentApp;
import com.project.app.patient.model.FXPatient;
import com.project.app.patient.viewmodel.PatientViewModel;
import com.project.app.util.CheckUtil;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author dhelarius 17/7/2022
 * periodent
 */
public class PatientView implements FxmlView<PatientViewModel>, Initializable {

    @InjectViewModel
    private PatientViewModel viewModel;
    private Stage stage;
    private PeriodentApp periodentApp;

    // Filters
    @FXML private TextField idFilterField;
    @FXML private TextField nameFilterField;
    @FXML private TextField lastnameFilterField;
    @FXML private TextField dniFilterField;
    @FXML private CheckBox activeFilterCheckBox;

    // Table
    @FXML private TableView<FXPatient> patientsTable = new TableView<>();
    @FXML private TableColumn<FXPatient, Number> columnId = new TableColumn<>("ID");
    @FXML private TableColumn<FXPatient, String> columnName = new TableColumn<>("Nombre");
    @FXML private TableColumn<FXPatient, String> columnLastname = new TableColumn<>("Apellido");
    @FXML private TableColumn<FXPatient, StringConverter<LocalDate>> columnBirthday = new TableColumn<>("Fecha Nacimiento");
    @FXML private TableColumn<FXPatient, String> columnDni = new TableColumn<>("Cédula");
    @FXML private TableColumn<FXPatient, String> columnPhone = new TableColumn<>("Teléfono");
    @FXML private TableColumn<FXPatient, String> columnAddress = new TableColumn<>("Dirección");
    @FXML private TableColumn<FXPatient, String> columnEmail = new TableColumn<>("Email");
    @FXML private TableColumn<FXPatient, Boolean> columnActive = new TableColumn<>("Activo");

    public void setPeriodentApp(PeriodentApp periodentApp) {
        this.periodentApp = periodentApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel.getPatients().removeAll(viewModel.getPatients());
        viewModel.loadPatients();

        initPatientsColumns();
        initFilters();
        handleEvents();
    }

    private void initPatientsColumns() {
        columnId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnLastname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        columnBirthday.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        columnDni.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
        columnPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        columnAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        columnActive.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
    }

    private void initFilters() {

        FilteredList<FXPatient> filteredData = new FilteredList<>(viewModel.getPatients(), p -> true);

        idFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(patient -> {
                // Si el campo está vacío, muestra a todos los usuarios.
                if (newValue == null || newValue.isEmpty() || CheckUtil.isNumber(newValue)) {
                    return true;
                }

                // Compara el código de cada usuario con el valor del campo.
                int id = Integer.parseInt(newValue);

                if (patient.getId() == id) {
                    return true; // Filtra las coincidencias con el código de usuario.
                }
                return false; // Sin coincidencias.
            });
        });

        nameFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(patient -> {
                // Si el campo está vacío, muestra a todos los usuarios.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                var name = newValue.toLowerCase();

                if(patient.getName().toLowerCase().contains(name)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        lastnameFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(patient -> {
                // Si el campo está vacío, muestra a todos los usuarios.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                var lastname = newValue.toLowerCase();

                if(patient.getLastname().toLowerCase().contains(lastname)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        dniFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(patient -> {
                // Si el campo está vacío, muestra a todos los usuarios.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                var dni = newValue.toLowerCase();

                if(patient.getDni().toLowerCase().contains(dni)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        activeFilterCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(patient -> {
                if(newValue) {
                    if(patient.isActive()) {
                        return true;
                    }
                } else {
                    if(!patient.isActive()) {
                        return true;
                    }
                }
                return false;
            });
        });

        SortedList<FXPatient> patiensData = new SortedList<>(filteredData);

        patiensData.comparatorProperty().bind(patientsTable.comparatorProperty());

        patientsTable.setItems(patiensData);
    }

    private void handleEvents() {
        patientsTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 1) {
                var patient = patientsTable.getSelectionModel().getSelectedItem();

                if(patient == null) return;

                viewModel.setPatient(patient);
            }

            if(mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                var patient = patientsTable.getSelectionModel().getSelectedItem();

                if(patient == null) return;

                viewModel.setPatient(patient);
                viewModel.setNewPatient(false);
                periodentApp.showEditPatientLayout();
            }
        });
    }

    @FXML
    public void handleNew() {
        viewModel.setNewPatient(true);
        viewModel.setPatient(new FXPatient());
        periodentApp.showEditPatientLayout();
    }
}
