package com.project.app.layout;

import com.project.app.PeriodentApp;
import javafx.fxml.FXMLLoader;

/**
 * @author dhelarius 13/4/2022
 * periodent
 */
public abstract class Layout {

    public enum Type {
        RootLayout
    }

    protected final FXMLLoader loader;

    public Layout(String resource) {
        loader = new FXMLLoader();
        loader.setLocation(PeriodentApp.class.getResource(resource));
    }

    public abstract void init();
}
