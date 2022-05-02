package com.project.app.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * @author dhelarius 1/5/2022
 * periodent
 */
public class DialogUtil {

    @FunctionalInterface
    public interface Action {
        void onConfirm();
    }

    public static void showMessage(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void showConfirmationMessage(String headerText, String contentText, Action action) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if(button.equals(ButtonType.OK)) {
            action.onConfirm();
        }
    }

    public static void showErrorMessage(Throwable throwable, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(throwable.getMessage());
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
