/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import interfaces.Subscriber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.settings.AlarmSettings;
import ui.settings.DataDownloadSettings;
import ui.settings.FleetSettings;
import ui.settings.FrequencySettings;
import utils.ThreadHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsPageController extends UIPageController implements Initializable, Subscriber {

    @FXML
    private StackPane uiWrapper;

    @FXML
    private VBox uiAlarmSettingsTab;

    @FXML
    private VBox uiDataSourceSettingsTab;

    @FXML
    private VBox uiFleetSettingsTab;

    @FXML
    private VBox uiFreqSettingsTab;

    @FXML
    private VBox uiLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ThreadHelper.func(() -> {

            AlarmSettings alarmSettings = new AlarmSettings();
            alarmSettings.initUI();

            ThreadHelper.runOnUIThread( () -> {
                uiAlarmSettingsTab.getChildren().add(alarmSettings.getUI());
                alarmSettings.fillForms();
            });

            FleetSettings fleetSettings = new FleetSettings();
            fleetSettings.initUI();

            ThreadHelper.runOnUIThread( () -> {
                uiFleetSettingsTab.getChildren().add(fleetSettings.getUI());
                fleetSettings.fillForms();
            });

            DataDownloadSettings dataDownloadSettings = new DataDownloadSettings();
            dataDownloadSettings.initUI();

            ThreadHelper.runOnUIThread( () -> {
                uiDataSourceSettingsTab.getChildren().add(dataDownloadSettings.getUI());
                dataDownloadSettings.fillForms();
            });

            FrequencySettings frequencySettings = new FrequencySettings();
            frequencySettings.initUI();

            ThreadHelper.runOnUIThread( () -> {
                uiFreqSettingsTab.getChildren().add(frequencySettings.getUI());
                frequencySettings.fillForms();
                uiWrapper.getChildren().remove(uiLoader);
            });
        });
    }
}
