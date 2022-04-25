package com.project.app.user.view;

import com.project.app.PeriodentApp;
import com.project.app.user.model.FXUser;
import com.project.app.user.viewmodel.UserViewModel;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author dhelarius 14/4/2022
 * periodent
 */
public class UserView implements FxmlView<UserViewModel>, Initializable {

    @InjectViewModel
    private UserViewModel viewModel;

    @FXML
    private TextField idFilterField;

    @FXML
    private TextField nameFilterField;

    @FXML
    private CheckBox statusCheckBox;

    @FXML
    private TableView<FXUser> usersTable = new TableView<>();

    @FXML private TableColumn<FXUser, Number> columnId = new TableColumn<>("ID");
    @FXML private TableColumn<FXUser, String> columnName = new TableColumn<>("Nombre");
    @FXML private TableColumn<FXUser, String> columnLastname = new TableColumn<>("Apellido");
    @FXML private TableColumn<FXUser, String> columnDNI = new TableColumn<>("Cédula");
    @FXML private TableColumn<FXUser, String> columnPhone = new TableColumn<>("Teléfono");
    @FXML private TableColumn<FXUser, String> columnEmail = new TableColumn<>("Email");
    @FXML private TableColumn<FXUser, String> columnUsername = new TableColumn<>("Usuario");
    @FXML private TableColumn<FXUser, String> columnCreatedBy = new TableColumn<>("Creado Por");
    @FXML private TableColumn<FXUser, Timestamp> columnCreationDate = new TableColumn<>("Fecha Creación");
    @FXML private TableColumn<FXUser, Boolean> columnStatus = new TableColumn<>("Activo");

    private PeriodentApp periodentApp;

    public void setPeriodentApp(PeriodentApp periodentApp) {
        this.periodentApp = periodentApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        viewModel.getUsers().removeAll(viewModel.getUsers());
        viewModel.loadUsers();

        initUserColumns();

        filter();

        usersTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                var user = usersTable.getSelectionModel().getSelectedItem();
                viewModel.setUser(user);
                periodentApp.showEditUserLayout();
            }
        });
    }

    private void initUserColumns() {
        columnId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnLastname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        columnDNI.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
        columnPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        columnUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        columnCreatedBy.setCellValueFactory(cellData -> cellData.getValue().createdByProperty());
        columnCreationDate.setCellValueFactory(cellData -> cellData.getValue().creationDateProperty());
        columnStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        columnStatus.setCellFactory(cell -> new CheckBoxTableCell<>());
    }

    private void filter() {

        FilteredList<FXUser> filteredData = new FilteredList<>(viewModel.getUsers(), p -> true);

        statusCheckBox.setSelected(true);

        idFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {

                // Si el campo está vacío, muestra a todos los usuarios.
                if (newValue == null || newValue.isEmpty() || !isNumber(newValue)) {
                    return true;
                }

                // Compara el código de cada usuario con el valor del campo.
                int id = Integer.parseInt(newValue);

                if (user.getId() == id) {
                    return true; // Filtra las coincidencias con el código de usuario.
                }
                return false; // Sin coincidencias.
            });
        });

        nameFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                // Si el campo está vacío, muestra a todos los usuarios.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                var name = newValue.toLowerCase();

                if(user.getName().toLowerCase().contains(name)) {
                    return true;
                } else if(user.getLastname().toLowerCase().contains(name)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        statusCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                if(newValue) {
                    return true;
                }
                return false;
            });
        });

        SortedList<FXUser> usersData = new SortedList<>(filteredData);

        usersTable.setItems(usersData);
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @FXML
    public void handleNewUser() {
        // TODO: Crear nuevo usuario
    }
}
