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

public class FleetSettingsController extends SettingsTabController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCommonEvents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void submitForm() {
        SharedConfig.SETTINGS.put("plan_download_url", uiPlanUrlInput.getText());
        SharedConfig.SETTINGS.put("pdks_download_url", uiPDKSInput.getText());
        SharedConfig.SETTINGS.put("speed_download_url", uiSpeedUrlInput.getText());
        SharedConfig.SETTINGS.put("message_out_download_url", uiMessageOutInput.getText());
        SharedConfig.SETTINGS.put("message_in_download_url", uiMessageInInput.getText());
        SharedConfig.SETTINGS.put("driver_details_url", uiDriverDetailsUrlInput.getText());
        SharedConfig.SETTINGS.put("paralel_downloader_limit", Integer.valueOf(uiDownloadLimitInput.getText()));
        SharedConfig.overwriteStaticSettings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillForms() {
        uiPlanUrlInput.setText(SharedConfig.SETTINGS.getString("plan_download_url"));
        uiPDKSInput.setText(SharedConfig.SETTINGS.getString("pdks_download_url"));
        uiSpeedUrlInput.setText(SharedConfig.SETTINGS.getString("speed_download_url"));
        uiMessageOutInput.setText(SharedConfig.SETTINGS.getString("message_out_download_url"));
        uiMessageInInput.setText(SharedConfig.SETTINGS.getString("message_in_download_url"));
        uiDriverDetailsUrlInput.setText(SharedConfig.SETTINGS.getString("driver_details_url"));
        uiDownloadLimitInput.setText(String.valueOf(SharedConfig.SETTINGS.getInt("paralel_downloader_limit")));
    }

}
