package com.project.app.specialist.view;

import com.project.app.PeriodentApp;
import com.project.app.specialist.viewmodel.SpecialistViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author dhelarius 4/9/2022
 * periodent
 */
public class SpecialistView implements FxmlView<SpecialistViewModel>, Initializable {

    @InjectViewModel
    private SpecialistViewModel viewModel;

    private PeriodentApp periodentApp;
    private Stage stage;

    public void setPeriodentApp(PeriodentApp periodentApp) {
        this.periodentApp = periodentApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleOpenEditSpecialty() {
        periodentApp.showEditSpecialtyLayout();
    }
}
