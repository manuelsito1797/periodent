package com.project.app.user.layout;

import com.project.app.layout.Layout;
import com.project.app.user.view.UserView;
import com.project.app.user.viewmodel.UserViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author dhelarius 14/4/2022
 * periodent
 */
public class UserLayout extends Layout {

    public UserLayout(String resource) {
        super(resource);
    }

    public void init() {
        ViewTuple<UserView, UserViewModel> viewTuple = FluentViewLoader.fxmlView(UserView.class).load();
        var userLayout = viewTuple.getView();

        // Crear escenario
        stage = new Stage();
        stage.setTitle("Usuario");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(window);
        stage.setScene(new Scene(userLayout));
        stage.showAndWait();
    }

    @Override
    public Stage getStage() {
        return null;
    }
}
