package com.project.app.controller;

import com.project.app.PeriodentApp;
import com.project.app.util.StageEventUtil;
import com.project.domain.user.preferences.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.Optional;

import javafx.stage.Stage;

/**
 * @author dhelarius 13/4/2022
 * periodent
 */
public class RootLayoutController {

    // Referencia a la aplicacion principal
    private PeriodentApp periodentApp;

    private Stage stage;

    /**
     * Si es llamada por la aplicacion principal debe dar referencia de si misma
     * @param periodentApp
     */
    public void setPeriodentApp(PeriodentApp periodentApp) {
        this.periodentApp = periodentApp;
    }

    public void setStage (Stage stage) {
        this.stage = stage;
        StageEventUtil.setOnCloseRequest(this::showExitDialog, stage);
    }

    @FXML
    private Menu permissionsMenu = new Menu();

    @FXML
    private MenuItem usersMenuItem = new MenuItem();

    @FXML
    private void initialize() {
        var preferences = UserPreferences.getInstance();

        preferences.restrictFromPermission(permission -> {
            usersMenuItem.setVisible(permission.test("ADMIN_USERS"));
            permissionsMenu.setVisible(permission.test("ADMIN_PERMISSIONS"));
        });
    }

    @FXML
    public void handleOpenUserView() {
        periodentApp.showUserLayout();
    }

    @FXML
    public void handleOpenPermissionView() {
        periodentApp.showPermissionLayout();
    }

    @FXML
    public void handleOpenBulkAssignmentPermissionsView() {
        periodentApp.showPermissionsAssignedLayout();
    }

    private int showExitDialog() {
        var exit = 0;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("¿Desea cerrar la aplicación?");

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (ButtonType.OK.equals(button)) {
            System.exit(0);
        } else {
            exit = -1;
        }

        return exit;
    }

    /**
     * Cerrar aplicación
     */
    @FXML
    public void handleExit() {
        showExitDialog();
    }
}
