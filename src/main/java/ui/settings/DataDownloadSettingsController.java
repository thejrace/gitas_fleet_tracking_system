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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        } else {
            uiDataSourceBtn.setText("Aktif Kaynak: Sunucu");
        }
    }

}
