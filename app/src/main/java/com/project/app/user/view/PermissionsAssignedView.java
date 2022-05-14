package com.project.app.user.view;

import com.project.app.user.viewmodel.UserViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author dhelarius 2/5/2022
 * periodent
 */
public class PermissionsAssignedView implements FxmlView<UserViewModel>, Initializable {

    @InjectViewModel
    private UserViewModel viewModel;

    @FXML
    private TreeView treeView = new TreeView();

    private TreeItem rootItem = new TreeItem("Usuarios");

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rootItem.getChildren().add(new TreeItem<>("Rahuel Rosario | RR"));
        rootItem.getChildren().add(new TreeItem<>(new CheckBox("Benjamin Moran | BM")));
        treeView.setRoot(rootItem);
    }
}
