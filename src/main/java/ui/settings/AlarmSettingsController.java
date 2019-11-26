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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import utils.SharedConfig;

import java.net.URL;
import java.util.ResourceBundle;

public class AlarmSettingsController extends SettingsTabController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillForms(){
        uiAlarmFreqInput.setText(String.valueOf(SharedConfig.SETTINGS.getInt("alarm_frequency")));
        uiAlarmVisibleFreqInput.setText(String.valueOf(SharedConfig.SETTINGS.getInt("alarm_visible_delay")));

        String alarmString = SharedConfig.SETTINGS.getString("enabled_alarms");

        if( alarmString.charAt(0) == '1') uiAlarmTypeZayiCB.setSelected(true);
        if( alarmString.charAt(1) == '1') uiAlarmTypeLateCB.setSelected(true);
        if( alarmString.charAt(2) == '1') uiAlarmTypeFixedCB.setSelected(true);
    }

}
