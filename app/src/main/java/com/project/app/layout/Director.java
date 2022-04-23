package com.project.app.layout;

import com.project.app.PeriodentApp;
import com.project.app.controller.LoginDialogController;
import com.project.app.controller.RootLayoutController;
import com.project.app.user.view.EditUserView;
import com.project.app.user.view.UserView;
import com.project.app.user.viewmodel.UserViewModel;
import com.project.domain.user.preferences.UserPreferences;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author dhelarius 17/4/2022
 * periodent
 */
public class Director {

    private Stage rootFromRootLayout;
    private Stage rootFromUserLayout;

    private final PeriodentApp periodentApp;

    public Director(PeriodentApp periodentApp) {
        this.periodentApp = periodentApp;
    }

    public void constructLoginLayout(Builder builder) {
        try {
            // Load login dialog from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PeriodentApp.class.getResource("view/login-dialog.fxml"));
            AnchorPane loginDialog = (AnchorPane) loader.load();

            var stage = new Stage();
            LoginDialogController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setPeriodentApp(periodentApp);

            // Crear dialog
            builder.setLayoutType(LayoutType.LOGIN_LAYOUT);
            builder.setStage(stage);
            builder.setParent(loginDialog);
            builder.setTitle("Iniciar sesion");
            builder.setModality(Modality.WINDOW_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void constructRootLayout(Builder builder) {
        try {
            var userPreferences = UserPreferences.getInstance();
            var user = userPreferences.getUserFromPreference();

            var name = user.getName();
            var lastname = user.getLastname();

            // Load login dialog from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PeriodentApp.class.getResource("view/root-layout.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            RootLayoutController controller = loader.getController();
            controller.setPeriodentApp(periodentApp);

            var stage = new Stage();
            rootFromRootLayout = stage;
            builder.setLayoutType(LayoutType.ROOT_LAYOUT);
            builder.setStage(stage);
            builder.setParent(rootLayout);
            builder.setTitle("Periodental - " + name + " " + lastname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void constructUserLayout(Builder builder) {
        ViewTuple<UserView, UserViewModel> viewTuple = FluentViewLoader.fxmlView(UserView.class).load();
        var userLayout = viewTuple.getView();

        var stage = new Stage();
        rootFromUserLayout = stage;
        var userView = viewTuple.getCodeBehind();
        userView.setPeriodentApp(periodentApp);

        builder.setLayoutType(LayoutType.USER_LAYOUT);
        builder.setStage(stage);
        builder.setParent(userLayout);
        builder.setTitle("Usuario");
        builder.setModality(Modality.WINDOW_MODAL);
        builder.setOwner(rootFromRootLayout);
    }

    public void constructEditUserLayout(Builder builder) {
        ViewTuple<EditUserView, UserViewModel> viewTuple = FluentViewLoader.fxmlView(EditUserView.class).load();
        var editUserLayout = viewTuple.getView();

        var stage = new Stage();
        var editUserView = viewTuple.getCodeBehind();
        editUserView.setDialogStage(stage);

        builder.setLayoutType(LayoutType.EDIT_USER_LAYOUT);
        builder.setStage(stage);
        builder.setParent(editUserLayout);
        builder.setTitle("Usuario");
        builder.setModality(Modality.WINDOW_MODAL);
        builder.setOwner(rootFromUserLayout);
    }
}
