package com.project.app.controller;

import com.project.app.PeriodentApp;
import com.project.domain.user.preferences.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

import java.util.Optional;

/**
 * @author dhelarius 13/4/2022
 * periodent
 */
public class RootLayoutController {

    // Referencia a la aplicacion principal
    private PeriodentApp periodentApp;

    /**
     * Si es llamada por la aplicacion principal debe dar referencia de si misma
     * @param periodentApp
     */
    public void setPeriodentApp(PeriodentApp periodentApp) {
        this.periodentApp = periodentApp;
    }

    @FXML
    private MenuItem permissionsMenuItem = new MenuItem();

    @FXML
    private MenuItem usersMenuItem = new MenuItem();

    @FXML
    private void initialize() {
        var preferences = UserPreferences.getInstance();

        preferences.restrictFromPermission(permission -> {
            usersMenuItem.setVisible(permission.test("ADMIN_USERS"));
            permissionsMenuItem.setVisible(permission.test("ADMIN_PERMISSIONS"));
        });
    }

    @FXML
    public void handleOpenUserView() {
        periodentApp.showUserLayout();
    }

    /**
     * Cerrar aplicacion
     */
    @FXML
    public void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("¿Desea cerrar la aplicación?");

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if(button == ButtonType.OK) {
            System.exit(0);
        }
    }
}
