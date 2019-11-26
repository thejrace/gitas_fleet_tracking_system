package ui.start_splash;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ui.UIComponent;

import java.net.URL;
import java.util.ResourceBundle;

public class StartSplashScreenController extends UIComponent implements Initializable {

    @FXML
    private Label uiNotfLabel;

    @FXML
    private Label uiSubNotfLabel;

    private StringProperty notf;
    private StringProperty subNotf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notf = new SimpleStringProperty(this, "...");
        subNotf = new SimpleStringProperty(this, "...");

        uiNotfLabel.textProperty().bindBidirectional(notf);
        uiSubNotfLabel.textProperty().bindBidirectional(subNotf);
    }

    public void updateStatus(String notf, String subNotf){
        Platform.runLater(() -> {
            this.notf.setValue(notf);
            this.subNotf.setValue(subNotf);
        });
    }

}
