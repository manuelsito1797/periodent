package com.project.app.layout;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author dhelarius 13/4/2022
 * periodent
 */
public class RootLayout extends Layout {

    public RootLayout(String resource) {
        super(resource);
    }

    @Override
    public void init() {
        try {
            // Show the scene containing the root layout.
            var rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            var stage = new Stage();
            stage.setTitle("Periodental");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
