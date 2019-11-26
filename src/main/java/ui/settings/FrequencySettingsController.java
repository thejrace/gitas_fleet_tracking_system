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
import javafx.scene.control.TextField;
import utils.SharedConfig;

import java.net.URL;
import java.util.ResourceBundle;

public class FrequencySettingsController extends SettingsTabController implements Initializable {

    @FXML
    private TextField uiPlanFreqInput;

    @FXML
    private TextField uiPDKSFreqInput;

    @FXML
    private TextField uiSpeedFreqInput;

    @FXML
    private TextField uiPlateFreqInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCommonEvents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void submitForm(){
        SharedConfig.SETTINGS.put("plan_download_freq", Integer.valueOf(uiPlanFreqInput.getText()));
        SharedConfig.SETTINGS.put("pdks_download_freq", Integer.valueOf(uiPDKSFreqInput.getText()));
        SharedConfig.SETTINGS.put("speed_download_freq", Integer.valueOf(uiSpeedFreqInput.getText()));
        SharedConfig.SETTINGS.put("plate_download_freq", Integer.valueOf(uiPlateFreqInput.getText()));
        SharedConfig.overwriteStaticSettings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillForms() {
        uiPlanFreqInput.setText(String.valueOf(SharedConfig.SETTINGS.getInt("plan_download_freq")));
        uiPDKSFreqInput.setText(String.valueOf(SharedConfig.SETTINGS.getInt("pdks_download_freq")));
        uiSpeedFreqInput.setText(String.valueOf(SharedConfig.SETTINGS.getInt("speed_download_freq")));
        uiPlateFreqInput.setText(String.valueOf(SharedConfig.SETTINGS.getInt("plate_download_freq")));
    }
}
