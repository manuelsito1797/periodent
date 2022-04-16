package com.project.app.layout;

import com.project.app.PeriodentApp;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author dhelarius 13/4/2022
 * periodent
 */
public abstract class Layout {

    public enum Type {
        RootLayout,
        UserLayout
    }

    private final FXMLLoader loader;
    protected Stage stage;
    protected Window window;

    public Layout(String resource) {
        loader = new FXMLLoader();
        loader.setLocation(PeriodentApp.class.getResource(resource));
        stage = new Stage();
    }

    public abstract void init();

    public Stage getStage() {
        return stage;
    }

    public void setOwner(Window window) {
        this.window = window;
    }

    public FXMLLoader getLoader() {
        return loader;
    }
}
