package com.project.app.user.view;

import com.project.app.PeriodentApp;
import com.project.app.user.model.permission.FXPermission;
import com.project.app.util.CheckUtil;
import com.project.app.util.DialogUtil;
import de.saxsys.mvvmfx.FxmlView;
import com.project.app.user.viewmodel.PermissionViewModel;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author dhelarius 25/4/2022
 * periodent
 */
public class PermissionView implements FxmlView<PermissionViewModel>, Initializable {

    @InjectViewModel
    private PermissionViewModel viewModel;

    private PeriodentApp periodentApp;

    public void setPeriodentApp(PeriodentApp periodentApp) {
        this.periodentApp = periodentApp;
    }

    @FXML
    private TextField idFilterField;

    @FXML
    private TextField descriptionFilterField;

    @FXML
    private TableView<FXPermission> permissionsTable = new TableView<>();

    @FXML private TableColumn<FXPermission, Number> columnId = new TableColumn<>("ID");
    @FXML private TableColumn<FXPermission, String> columnDescription = new TableColumn<>("Descripcion");
    @FXML private TableColumn<FXPermission, String> columnKey = new TableColumn<>("Clave");
    @FXML private TableColumn<FXPermission, Boolean> columnActive = new TableColumn<>("Activo");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel.getPermissions().removeAll(viewModel.getPermissions());
        viewModel.loadPermissions();

        initPermissionsColumns();

        initFilters();

        handleEvents();
    }

    private void initPermissionsColumns() {
        columnId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        columnDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        columnKey.setCellValueFactory(cellData -> cellData.getValue().keyProperty());
        columnActive.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
        columnActive.setCellFactory(cell -> new CheckBoxTableCell<>());
    }

    private void initFilters() {

        FilteredList<FXPermission> filteredData = new FilteredList<>(viewModel.getPermissions(), p -> true);

        idFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(permission -> {
                // Si el campo está vacío, muestra a todos los usuarios.
                if (newValue == null || newValue.isEmpty() || CheckUtil.isNumber(newValue)) {
                    return true;
                }

                // Compara el código de cada usuario con el valor del campo.
                int id = Integer.parseInt(newValue);

                if (permission.getId() == id) {
                    return true; // Filtra las coincidencias con el código de usuario.
                }
                return false; // Sin coincidencias.
            });
        });

        descriptionFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(permission -> {
                // Si el campo está vacío, muestra a todos los usuarios.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                var description = newValue.toLowerCase();

                if(permission.getDescription().toLowerCase().contains(description)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<FXPermission> permissionsData = new SortedList<>(filteredData);

        permissionsData.comparatorProperty().bind(permissionsTable.comparatorProperty());

        permissionsTable.setItems(permissionsData);
    }

    private void handleEvents() {
        permissionsTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 1) {
                var permission = permissionsTable.getSelectionModel().getSelectedItem();

                if(permission == null) return;

                viewModel.setPermission(permission);
            }

            if(mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                var permission = permissionsTable.getSelectionModel().getSelectedItem();

                if(permission == null) return;

                viewModel.setPermission(permission);
                viewModel.setNewPermission(false);
                periodentApp.showEditPermissionLayout();
            }
        });
    }

    @FXML
    public void handleNew() {
        viewModel.setNewPermission(true);
        viewModel.setPermission(new FXPermission());
        periodentApp.showEditPermissionLayout();
    }

    @FXML
    public void handleDelete() {
        // TODO: Eliminar permiso
        DialogUtil.showConfirmationMessage(
                "¿Desea eliminar el permiso?", "", this::deletePermission);
    }

    private void deletePermission() {
        viewModel.deletePermission((response, throwable) -> {
            if(throwable != null) {
                var seeAssignedPermissions = new ButtonType("Ver Permisos Asignados");

                Alert alert = new Alert(Alert.AlertType.WARNING, throwable.getMessage(),
                        seeAssignedPermissions, ButtonType.CANCEL);
                alert.setHeaderText("Permiso Asignado");
                ButtonType button = alert.showAndWait().orElse(ButtonType.CANCEL);

                if(button.equals(seeAssignedPermissions)) {
                    System.out.println("Ver permisos asignados");
                }
                return;
            }

            viewModel.getPermissions().removeIf(permission -> {
                if(permission.getId() == response.getId()) {
                    return true;
                }
                return false;
            });
            System.out.println("Deleted: " + response);
            DialogUtil.showMessage("Permiso Eliminado", "!Se ha eliminado el permiso satisfactoriamente¡");
        });
    }
}
