package com.project.app.util;

import javafx.stage.WindowEvent;
import javafx.stage.Stage;

/**
 * @author dhelarius 30/4/2022
 * periodent
 */
public class StageEventUtil {

    @FunctionalInterface
    public interface Action {
        int exec();
    }

    public static void setOnCloseRequest(Action action, Stage stage) {
        stage.setOnCloseRequest(windowEvent -> {
            if(windowEvent.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST) {
                int value = action.exec();
                if (value == -1) {
                    windowEvent.consume();
                }
            }
        });
    }
}
