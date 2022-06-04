package com.project.app.user.view;

import com.project.app.user.model.FXUser;
import com.project.app.user.model.permission.FXPermission;
import com.project.app.user.viewmodel.PermissionViewModel;
import com.project.app.user.viewmodel.UserViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author dhelarius 2/5/2022
 * periodent
 */
public class PermissionsAssignedView implements FxmlView<UserViewModel>, Initializable {

    @InjectViewModel
    private UserViewModel viewModel;

    private final PermissionViewModel permissionViewModel = new PermissionViewModel();

    @FXML
    private ChoiceBox<FXPermission> choicePermissions = new ChoiceBox<>();

    @FXML
    private TableView<FXUser> usersTable = new TableView<>();

    @FXML private TableColumn<FXUser, CheckBox> columnSelection = new TableColumn<>("CheckBox");
    @FXML private TableColumn<FXUser, String> columnName = new TableColumn<>("Nombre");
    @FXML private TableColumn<FXUser, String> columnLastname = new TableColumn<>("Apellido");
    @FXML private TableColumn<FXUser, String> columnUsername = new TableColumn<>("Usuario");

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        permissionViewModel.loadPermissions();
        var permissions = permissionViewModel.getPermissions();

        choicePermissions.getItems().addAll(permissions.stream().toList());

        choicePermissions.setConverter(new StringConverter<>() {
            @Override
            public String toString(FXPermission permission) {
                if(permission == null) return null;
                return permission.getDescription();
            }

            @Override
            public FXPermission fromString(String s) {
                return null;
            }
        });

        viewModel.getUsers().removeAll(viewModel.getUsers());
        viewModel.loadUsers();

        initUserColumns();

        usersTable.setItems(viewModel.getUsers());
    }

    private void initUserColumns() {
        columnSelection.setCellValueFactory(cell -> {
            var user = cell.getValue();
            var checkbox = new CheckBox();
            checkbox.selectedProperty().setValue(user.isSelected());
            checkbox.selectedProperty().addListener((observableValue, oldValue, newValue) ->
                    user.setSelected(newValue));
            return new SimpleObjectProperty<>(checkbox);
        });
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnLastname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        columnUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
    }

    @FXML
    public void selectAll() {
        System.out.println("Seleccionar todos");
    }

    @FXML
    public void deselectAll() {
        System.out.println("Deseleccionar todo");
    }

    @FXML
    public void assingPermission() {
        System.out.println("Assignar permiso");
    }

    @FXML
    public void removePermission() {
        System.out.println("Quitar permiso");
    }
}
