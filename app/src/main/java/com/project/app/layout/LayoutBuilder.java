package com.project.app.layout;

import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author dhelarius 17/4/2022
 * periodent
 */
public class LayoutBuilder implements Builder {

    private LayoutType layoutType;
    private Stage stage;
    private Parent parent;
    private String title;
    private Modality modality;
    private Window owner;

    @Override
    public void setLayoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setModality(Modality modality) {
        this.modality = modality;
    }

    @Override
    public void setOwner(Window owner) {
        this.owner = owner;
    }

    public Layout getResult() {
        return new Layout(layoutType, stage, parent, title, modality, owner);
    }
}
