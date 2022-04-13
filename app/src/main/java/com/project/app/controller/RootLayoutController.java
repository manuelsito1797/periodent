package com.project.app.controller;

import com.project.app.PeriodentApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
