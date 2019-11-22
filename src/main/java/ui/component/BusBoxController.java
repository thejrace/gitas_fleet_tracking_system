/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import enums.BusRunStatus;
import enums.BusRunStatusStyleClass;
import interfaces.MultipleActionCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import models.Bus;
import ui.custom_control.BusBoxButton;
import utils.Common;
import utils.ThreadHelper;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class BusBoxController implements Initializable {

    private Bus bus;

    @FXML
    private Circle uiLed;

    @FXML
    private Label uiBusCodeLabel;

    @FXML
    private Label uiPlateLabel;

    @FXML
    private Label uiRouteLabel;

    @FXML private Label uiNotfLabel;

    @FXML private Label uiSubNotfLabel;

    @FXML
    private BusBoxButton uiBB0, uiBB1, uiBB2, uiBB3, uiBB4, uiBB5;

    @FXML private Label uiSummary0, uiSummary1, uiSummary2, uiSummary3, uiSummary4;

    @FXML private Button uiFleetDataDownloadBtn;

    @FXML private Button uiPlateDataDownloadBtn;

    @FXML private Button uiDataDownloadLogBtn;

    @FXML private Label uiFleetDataDownloadTimestampLabel;

    @FXML private Label uiPlateDataDownloadTimestampLabel;

    private MultipleActionCallback multipleActionCallback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        uiFleetDataDownloadBtn.setOnMouseClicked( ev -> {
            multipleActionCallback.onAction(0);
        });

        uiPlateDataDownloadBtn.setOnMouseClicked( ev -> {
            multipleActionCallback.onAction(1);
        });

    }



    public void setData(Bus bus, Map<String, Integer> runStatusSummary ){
        this.bus = bus;

        // @todo FIX this
        uiBB0.setKey(bus.getCode());
        uiBB1.setKey(bus.getCode());
        uiBB2.setKey(bus.getCode());
        uiBB3.setKey(bus.getCode());
        uiBB4.setKey(bus.getCode());
        uiBB5.setKey(bus.getCode());

        uiSummary0.setText(String.valueOf(runStatusSummary.get(BusRunStatus.T)));
        uiSummary1.setText(String.valueOf(runStatusSummary.get(BusRunStatus.B)));
        uiSummary2.setText(String.valueOf(runStatusSummary.get(BusRunStatus.A)));
        uiSummary3.setText(String.valueOf(runStatusSummary.get(BusRunStatus.Y)));
        uiSummary4.setText(String.valueOf(runStatusSummary.get(BusRunStatus.I)));

        uiFleetDataDownloadTimestampLabel.setText(Common.getCurrentHMin());

        updateUI();
    }

    public void setStatusData( String status, String statusLabel, String subStatusLabel ){
        ThreadHelper.runOnUIThread( () -> {
            uiNotfLabel.setText(statusLabel);
            uiSubNotfLabel.setText(subStatusLabel);
            uiLed.getStyleClass().add(1, BusRunStatusStyleClass.get(status));
        });
    }

    private void updateUI(){
        uiBusCodeLabel.setText(bus.getCode());
        uiPlateLabel.setText(bus.getActivePlate());
        uiRouteLabel.setText(bus.getRouteCode());
    }

    public void subscribeEvents(MultipleActionCallback multipleActionCallback){
        this.multipleActionCallback = multipleActionCallback;
    }

}
