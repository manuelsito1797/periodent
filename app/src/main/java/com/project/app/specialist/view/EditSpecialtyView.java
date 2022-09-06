package com.project.app.specialist.view;

import com.project.app.PeriodentApp;
import com.project.app.specialist.viewmodel.SpecialtyViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author dhelarius 4/9/2022
 * periodent
 */
public class EditSpecialtyView implements FxmlView<SpecialtyViewModel>, Initializable {

    @InjectViewModel
    private SpecialtyViewModel viewModel;

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
}
