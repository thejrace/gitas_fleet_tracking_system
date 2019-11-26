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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AlarmSettingsController implements Initializable {

    @FXML
    private TextField uiAlarmFreqInput;

    @FXML
    private TextField uiAlarmVisibleFreqInput;

    @FXML
    private CheckBox uiAlarmTypeZayiCB;

    @FXML
    private CheckBox uiAlarmTypeLateCB;

    @FXML
    private CheckBox uiAlarmTypeFixedCB;

    @FXML
    private Button uiSaveBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
