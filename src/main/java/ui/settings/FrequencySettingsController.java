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

public class FrequencySettingsController implements Initializable {

    @FXML
    private TextField uiPlanFreqInput;

    @FXML
    private TextField uiPDKSFreqInput;

    @FXML
    private TextField uiSpeedFreqInput;

    @FXML
    private TextField uiPlateFreqInput;

    @FXML
    private Button uiSaveBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
