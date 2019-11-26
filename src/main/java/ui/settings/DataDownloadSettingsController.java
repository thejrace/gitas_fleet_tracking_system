/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.settings;

import enums.DataSourceSettings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.SharedConfig;

import java.net.URL;
import java.util.ResourceBundle;

public class DataDownloadSettingsController extends SettingsTabController implements Initializable {

    @FXML
    private TextField uiFleetURLInput;


    @FXML
    private TextField uiAPIUrlInput;

    @FXML
    private Button uiDataSourceBtn;

    private DataSourceSettings activeSetting;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCommonEvents();

        uiDataSourceBtn.setOnMouseClicked( ev -> {
            if( activeSetting == DataSourceSettings.FLEET ){
                uiDataSourceBtn.setText("Aktif Kaynak: Sunucu");
                activeSetting = DataSourceSettings.SERVER;
            } else {
                uiDataSourceBtn.setText("Aktif Kaynak: Filo5");
                activeSetting = DataSourceSettings.FLEET;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void submitForm() {
        SharedConfig.SETTINGS.put("fleet_cookie_url", uiFleetURLInput.getText());
        SharedConfig.SETTINGS.put("server_cookie_url", uiAPIUrlInput.getText());
        SharedConfig.SETTINGS.put("data_source", activeSetting.ordinal());
        SharedConfig.overwriteStaticSettings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillForms() {
        uiFleetURLInput.setText(SharedConfig.SETTINGS.getString("fleet_cookie_url"));
        uiAPIUrlInput.setText(SharedConfig.SETTINGS.getString("server_cookie_url"));

        int dataSourceSetting = SharedConfig.SETTINGS.getInt("data_source");
        if( dataSourceSetting == DataSourceSettings.FLEET.ordinal() ){
            uiDataSourceBtn.setText("Aktif Kaynak: Filo5");
            activeSetting = DataSourceSettings.FLEET;
        } else {
            uiDataSourceBtn.setText("Aktif Kaynak: Sunucu");
            activeSetting = DataSourceSettings.SERVER;
        }
    }

}
