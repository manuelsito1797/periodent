package com.project.app.layout;

import com.project.app.PeriodentApp;
import com.project.app.controller.LoginDialogController;
import com.project.app.controller.RootLayoutController;
import com.project.app.user.view.*;
import com.project.app.user.viewmodel.PermissionViewModel;
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

    private final String LOG = this.getClass().getSimpleName() + ": ";

    private final PeriodentApp periodentApp;

    // Root layouts
    private Stage rootFromRootLayout;
    private Stage rootFromUserLayout;
    private Stage rootFromPermissionLayout;

    // ViewModel
    private final UserViewModel userViewModel = new UserViewModel();

    private final PermissionViewModel permissionViewModel = new PermissionViewModel();

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
            builder.setModality(Modality.NONE);
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

            var stage = new Stage();
            rootFromRootLayout = stage;
            RootLayoutController controller = loader.getController();
            controller.setPeriodentApp(periodentApp);
            controller.setStage(stage);

            builder.setLayoutType(LayoutType.ROOT_LAYOUT);
            builder.setStage(stage);
            builder.setParent(rootLayout);
            builder.setTitle("Periodental - " + name + " " + lastname);
            builder.setModality(Modality.NONE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void constructUserLayout(Builder builder) {
        ViewTuple<UserView, UserViewModel> viewTuple = FluentViewLoader.fxmlView(UserView.class)
                .viewModel(userViewModel).load();
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
        ViewTuple<EditUserView, UserViewModel> viewTuple = FluentViewLoader.fxmlView(EditUserView.class)
                .viewModel(userViewModel).load();
        var editUserLayout = viewTuple.getView();

        var stage = new Stage();
        stage.setResizable(false);
        var editUserView = viewTuple.getCodeBehind();
        editUserView.setDialogStage(stage);

        builder.setLayoutType(LayoutType.EDIT_USER_LAYOUT);
        builder.setStage(stage);
        builder.setParent(editUserLayout);
        builder.setTitle("Usuario");
        builder.setModality(Modality.WINDOW_MODAL);
        builder.setOwner(rootFromUserLayout);
    }

    public void constructPermissionLayout(Builder builder) {
        ViewTuple<PermissionView, PermissionViewModel> viewTuple =FluentViewLoader.fxmlView(PermissionView.class)
                .viewModel(permissionViewModel).load();

        var stage = new Stage();
        stage.setResizable(false);
        rootFromPermissionLayout = stage;
        var permissionLayout = viewTuple.getView();
        var permissionView = viewTuple.getCodeBehind();
        permissionView.setPeriodentApp(periodentApp);

        builder.setLayoutType(LayoutType.PERMISSION_LAYOUT);
        builder.setStage(stage);
        builder.setParent(permissionLayout);
        builder.setTitle("Permisos");
        builder.setModality(Modality.WINDOW_MODAL);
        builder.setOwner(rootFromRootLayout);
    }

    public void constructEditPermissionLayout(Builder builder) {
        ViewTuple<EditPermissionView, PermissionViewModel> viewTuple = FluentViewLoader.fxmlView(EditPermissionView.class)
                .viewModel(permissionViewModel).load();

        var stage = new Stage();
        stage.setResizable(false);
        var editPermissionLayout = viewTuple.getView();
        var editPermissionView = viewTuple.getCodeBehind();
        editPermissionView.setStage(stage);

        builder.setLayoutType(LayoutType.EDIT_PERMISSION_LAYOUT);
        builder.setStage(stage);
        builder.setParent(editPermissionLayout);
        builder.setTitle("Nuevo Permiso");
        builder.setModality(Modality.WINDOW_MODAL);
        builder.setOwner(rootFromPermissionLayout);
    }

    public void constructPermissionsAssignedLayout(Builder builder) {
        ViewTuple<PermissionsAssignedView, UserViewModel> viewTuple = FluentViewLoader.fxmlView(PermissionsAssignedView.class)
                .viewModel(userViewModel).load();

        var stage = new Stage();
        var permissionsAssignedLayout = viewTuple.getView();
        var permissionsAssignedView = viewTuple.getCodeBehind();
        permissionsAssignedView.setStage(stage);

        builder.setLayoutType(LayoutType.PERMISSIONS_ASSIGNED_LAYOUT);
        builder.setStage(stage);
        builder.setParent(permissionsAssignedLayout);
        builder.setTitle("Asignación Masiva de Permisos");
        builder.setModality(Modality.NONE);
        builder.setOwner(rootFromPermissionLayout);
    }
}
