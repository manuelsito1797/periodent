package com.project.app.layout;

import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author dhelarius 17/4/2022
 * periodent
 */
public interface Builder {

    void setLayoutType(LayoutType layoutType);

    void setStage(Stage stage);

    void setParent(Parent parent);

    void setTitle(String title);

    void setModality(Modality modality);

    void setOwner(Window owner);
}
