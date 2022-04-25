package com.project.app.layout;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author dhelarius 17/4/2022
 * periodent
 */
public class Layout {

    private final LayoutType layoutType;
    private final Stage stage;
    private final Parent parent;
    private final String title;
    private final Modality modality;
    private final Window owner;

    public Layout(LayoutType layoutType, Stage stage, Parent parent,
                  String title, Modality modality, Window owner) {
        this.layoutType = layoutType;
        this.stage = stage;
        this.parent = parent;
        this.title = title;
        this.modality = modality;
        this.owner = owner;
    }

    public void show() {
        stage.setTitle(title);

        stage.initModality(modality);

        if(owner != null)
        stage.initOwner(owner);

        stage.setScene(new Scene(parent));

        switch (modality) {
            case NONE: stage.show(); break;
            default: stage.showAndWait();
        }
    }

    public LayoutType getLayoutType() {
        return layoutType;
    }

    public Stage getStage() {
        return stage;
    }
}
