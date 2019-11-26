/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.settings;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FleetSettingsController implements Initializable {

    @FXML
    private TextField uiPlanUrlInput;

    @FXML
    private TextField uiPDKSInput;

    @FXML
    private TextField uiSpeedUrlInput;

    @FXML
    private TextField uiMessageOutInput;

    @FXML
    private TextField uiMessageInInput;

    @FXML
    private TextField uiDriverDetailsUrlInput;

    @FXML
    private TextField uiDownloadLimitInput;

    @FXML
    private Button uiSaveBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
