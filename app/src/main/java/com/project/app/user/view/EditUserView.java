package com.project.app.user.view;

import com.project.app.user.viewmodel.UserViewModel;
import com.project.app.util.DialogUtil;
import com.project.domain.user.preferences.UserPreferences;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author dhelarius 17/4/2022
 * periodent
 */
public class EditUserView implements FxmlView<UserViewModel>, Initializable {

    private final String LOG = this.getClass().getSimpleName() + ": ";

    @InjectViewModel
    private UserViewModel viewModel;

    @FXML private TextField nameField;

    @FXML private TextField lastnameField;

    @FXML private TextField dniField;

    @FXML private TextField phoneField;

    @FXML private TextField emailField;

    @FXML private TextField usernameField;

    @FXML private PasswordField passwordField = new PasswordField();

    @FXML private PasswordField confirmPasswordField = new PasswordField();

    private final ObservableList<CheckBox> checkBoxList = FXCollections.observableArrayList();
    @FXML private ListView<CheckBox> permissionListView;

    @FXML private Button saveButton = new Button();

    private Stage stage;

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var preferences = UserPreferences.getInstance();

        preferences.restrictFromPermission(permission -> {
            permissionListView.setDisable(!permission.test("ASSIGN_PERMISSIONS"));
        });

        nameField.textProperty().bindBidirectional(viewModel.getUser().nameProperty());
        lastnameField.textProperty().bindBidirectional(viewModel.getUser().lastnameProperty());
        dniField.textProperty().bindBidirectional(viewModel.getUser().dniProperty());
        phoneField.textProperty().bindBidirectional(viewModel.getUser().phoneProperty());
        emailField.textProperty().bindBidirectional(viewModel.getUser().emailProperty());
        usernameField.textProperty().bindBidirectional(viewModel.getUser().usernameProperty());
        passwordField.textProperty().bindBidirectional(viewModel.getUser().passwordProperty());

        var permissions = viewModel.getUser().getPermissions();
        for(var permission : permissions) {
            var item = new CheckBox();
            item.idProperty().bind(permission.idProperty().asString());
            item.textProperty().bind(permission.descriptionProperty());
            item.selectedProperty().bindBidirectional(permission.assignedProperty());
            checkBoxList.add(item);
        }
        permissionListView.getItems().addAll(checkBoxList);
    }

    @FXML
    public void handleSaveUser() {
        if(passwordField.getText().isEmpty() ^ confirmPasswordField.getText().isEmpty()) {
            var throwable = new Exception("Error al actualizar contraseña");
            DialogUtil.showErrorMessage(throwable, "Uno de los campos de contraseña esta vació.");
            return;
        }

        if(!passwordField.getText().isEmpty() && !confirmPasswordField.getText().isEmpty()) {
            if(!passwordField.getText().equals(confirmPasswordField.getText())) {
                var throwable = new Exception("Error al actualizar contraseña");
                DialogUtil.showErrorMessage(throwable, "La contraseña dada no coincide.");
                return;
            }
        }

        if(viewModel.isNewUser()) {
            // TODO: Guardar nuevo usuario
        } else {
            DialogUtil.showConfirmationMessage(
                    "¿Desea guardar los cambios realizados en el usuario?", "", this::update
            );
        }
    }

    @FXML
    public void handleClose() {
        stage.close();
    }

    private void update() {
        viewModel.update(((response, throwable) -> {
            if(throwable != null) {
                DialogUtil.showErrorMessage(throwable, "Error al actualizar usuario");
                return;
            }

            DialogUtil.showMessage("Actualización Exitosa", "¡Se ha actualizado el usuario satisfactoriamente!");
            passwordField.clear();
            stage.close();
        }));
    }
}
