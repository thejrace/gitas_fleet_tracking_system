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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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

    @FXML private VBox uiBusBoxWrapper;

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

    @FXML private VBox uiSummaryBlock;
    @FXML private FlowPane uiNavBlock;
    @FXML private AnchorPane uiDataControlBlock;

    private MultipleActionCallback multipleActionCallback;

    private boolean dataInitializedFlag = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        uiFleetDataDownloadBtn.setOnMouseClicked( ev -> {
            multipleActionCallback.onAction(0);
        });

        uiPlateDataDownloadBtn.setOnMouseClicked( ev -> {
            multipleActionCallback.onAction(1);
        });
    }



    public void setData(Bus bus  ){
        this.bus = bus;

        if( !dataInitializedFlag ){
            // @todo FIX this
            uiBB0.setKey(bus.getCode());
            uiBB1.setKey(bus.getCode());
            uiBB2.setKey(bus.getCode());
            uiBB3.setKey(bus.getCode());
            uiBB4.setKey(bus.getCode());
            uiBB5.setKey(bus.getCode());

            // set ID for UI manipulation
            uiBusBoxWrapper.setId(bus.getCode());

            dataInitializedFlag = true;
            System.out.println("do not set again");
        }

        updateUI();
    }

    /**
     * Update the status and summary UI data
     *
     * @param status
     * @param statusLabel
     * @param subStatusLabel
     */
    public void setStatusData( String status, String statusLabel, String subStatusLabel, Map<String, Integer> runStatusSummary ){
        ThreadHelper.runOnUIThread( () -> {
            uiNotfLabel.setText(statusLabel);
            uiSubNotfLabel.setText(subStatusLabel);
            uiLed.getStyleClass().add(1, BusRunStatusStyleClass.get(status));

            uiSummary0.setText(String.valueOf(runStatusSummary.get(BusRunStatus.T)));
            uiSummary1.setText(String.valueOf(runStatusSummary.get(BusRunStatus.B)));
            uiSummary2.setText(String.valueOf(runStatusSummary.get(BusRunStatus.A)));
            uiSummary3.setText(String.valueOf(runStatusSummary.get(BusRunStatus.Y)));
            uiSummary4.setText(String.valueOf(runStatusSummary.get(BusRunStatus.I)));

            uiFleetDataDownloadTimestampLabel.setText(Common.getCurrentHMin());
        });
    }

    /**
     * Update header UI data
     */
    private void updateUI(){
        uiBusCodeLabel.setText(bus.getCode());
        uiPlateLabel.setText(bus.getActivePlate());

        if( !bus.getActivePlate().equals(bus.getOfficialPlate()) ){
            uiPlateLabel.getStyleClass().add(1, "warning");
        }

        uiRouteLabel.setText(bus.getRouteCode());
    }

    /**
     * Connect datacontrol buttons to the BusBox to trigger BusController
     *
     * @param multipleActionCallback
     */
    public void subscribeEvents(MultipleActionCallback multipleActionCallback){
        this.multipleActionCallback = multipleActionCallback;
    }

    /**
     * Change the style of the busbox @todo try it with css style class
     *
     * @param style
     */
    public void applyStyle( int style ){
        switch(style){
            case 0:
                showBlock(uiSummaryBlock);
                showBlock(uiNavBlock);
                showBlock(uiDataControlBlock);
                break;
            case 1:
                showBlock(uiSummaryBlock);
                hideBlock(uiNavBlock);
                hideBlock(uiDataControlBlock);
                break;
            case 2:
                hideBlock(uiSummaryBlock);
                hideBlock(uiNavBlock);
                hideBlock(uiDataControlBlock);
                break;
        }
    }

    /**
     * Hides the given block of the BusBox
     *
     * @param block
     */
    private void hideBlock( Pane block ){
        block.setVisible(false);
        block.setPrefHeight(0);
        block.setMinHeight(0);
        block.setMaxHeight(0);
    }

    /**
     * Shows the given block of the BusBox
     *
     * @param block
     */
    private void showBlock( Pane block ){
        block.setVisible(true);
        block.setPrefHeight(Region.USE_COMPUTED_SIZE);
        block.setMinHeight(Region.USE_PREF_SIZE);
        block.setMaxHeight(Region.USE_PREF_SIZE);
    }



}
