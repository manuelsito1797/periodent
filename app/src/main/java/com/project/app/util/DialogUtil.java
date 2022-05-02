package com.project.app.util;

import javafx.scene.control.Alert;

/**
 * @author dhelarius 1/5/2022
 * periodent
 */
public class DialogUtil {
    public static void message(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void errorMessage(Throwable throwable, String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(throwable.getMessage());
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
