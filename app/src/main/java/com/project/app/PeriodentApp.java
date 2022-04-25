package com.project.app;

import com.project.app.layout.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.function.Consumer;

/**
 * @author dhelarius 12/4/2022
 * periodent
 */
public class PeriodentApp extends Application {

    private final String LOG = this.getClass().getSimpleName();

    private Director director;
    private LayoutBuilder builder;

    @Override
    public void start(Stage stage) {
        director = new Director(this);
        builder = new LayoutBuilder();
        showLoginDialog();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void showLoginDialog() {
        showLayout(director::constructLoginLayout);
    }

    public void showRootLayout() {
        showLayout(director::constructRootLayout);
    }

    public void showUserLayout() {
        showLayout(director::constructUserLayout);
    }

    public void showEditUserLayout() {
        showLayout(director::constructEditUserLayout);
    }

   private void showLayout(Consumer<Builder> consumer) {
        consumer.accept(builder);
        builder.getResult().show();
   }
}
