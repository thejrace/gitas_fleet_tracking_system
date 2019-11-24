/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import com.google.common.eventbus.Subscribe;
import enums.BusRunStatus;
import enums.BusRunStatusStyleClass;
import events.bus_box.BusSpeedDownloadFinishedEvent;
import events.bus_box.FleetDataDownloadEvent;
import events.bus_box.FleetDataDownloadFinishedEvent;
import events.bus_box.PlateUpdateEvent;
import interfaces.MultipleActionCallback;
import interfaces.Subscriber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import models.Bus;
import ui.custom_control.BusBoxButton;
import ui.popup_pages.BusPlateFormPopup;
import utils.Common;
import utils.GitasEventBus;
import utils.ThreadHelper;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class BusBoxController implements Initializable, Subscriber {

    /**
     * Bus model
     */
    private Bus bus;

    /**
     * Main wrapper VBox
     */
    @FXML private VBox uiBusBoxWrapper;

    /**
     * Summary block ( Notf labels and summaries )
     */
    @FXML private VBox uiSummaryBlock;

    /**
     * Navigation button block
     */
    @FXML private FlowPane uiNavBlock;

    /**
     * Bottom data download log block
     */
    @FXML private AnchorPane uiDataControlBlock;

    /**
     * LED indicator of the box
     */
    @FXML private Circle uiLed;

    /**
     * Bus code label
     */
    @FXML private Label uiBusCodeLabel;

    /**
     * Plate label
     */
    @FXML private Label uiPlateLabel;

    /**
     * Route code label
     */
    @FXML private Label uiRouteLabel;

    /**
     * Main status notf label.
     */
    @FXML private Label uiNotfLabel;

    /**
     * Sub status notf label
     */
    @FXML private Label uiSubNotfLabel;

    /**
     * Run summary labels
     */
    @FXML private Label uiSummary0, uiSummary1, uiSummary2, uiSummary3, uiSummary4;

    /**
     * BusBox navigation buttons
     */
    @FXML private BusBoxButton uiBB0, uiBB1, uiBB2, uiBB3, uiBB4, uiBB5;

    /**
     * Fleet data download trigger button
     */
    @FXML private Button uiFleetDataDownloadBtn;

    /**
     * Plate data download trigger button
     */
    @FXML private Button uiPlateDataDownloadBtn;

    /**
     * Data download logger trigger button
     */
    @FXML private Button uiDataDownloadLogBtn;

    /**
     * Last fleet data download timestamp label
     */
    @FXML private Label uiFleetDataDownloadTimestampLabel;

    /**
     * Last plate data download timestamp label
     */
    @FXML private Label uiPlateDataDownloadTimestampLabel;

    /**
     * Speed indicator label
     */
    @FXML private Label uiSpeedLabel;

    /**
     * Flag to check if BusBox is initialized with bus data
     */
    private boolean dataInitializedFlag = false;

    /**
     * Plate update form popup instance
     */
    private BusPlateFormPopup busPlateFormPopup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        uiFleetDataDownloadBtn.setOnMouseClicked( ev -> {
            GitasEventBus.post(new FleetDataDownloadEvent(bus));
        });

        uiPlateDataDownloadBtn.setOnMouseClicked( ev -> {
            GitasEventBus.post(new PlateUpdateEvent(bus));
        });

        uiPlateLabel.setOnMouseClicked( ev -> {
            // @todo check if opened before
            if( busPlateFormPopup == null ){
                busPlateFormPopup = new BusPlateFormPopup();
            }
            try {
                busPlateFormPopup.start( new Stage() );
                busPlateFormPopup.setData(bus);
            } catch( Exception e ){
                e.printStackTrace();
            }
        });
    }

    /**
     * Set initial data to the BusBox
     *
     * @param bus
     */
    public void setData(Bus bus){
        this.bus = bus;

        if( !dataInitializedFlag ){
            // set ID for UI manipulation
            uiBusBoxWrapper.setId(bus.getCode());

            updatePlateDownloadTimestamp();

            dataInitializedFlag = true;
        }

        // @todo FIX this
        uiBB0.setBus(this.bus); // we pass the updated model to the navigation buttons
        uiBB1.setBus(this.bus);
        uiBB2.setBus(this.bus);
        uiBB3.setBus(this.bus);
        uiBB4.setBus(this.bus);
        uiBB5.setBus(this.bus);

        updateBaseData();
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

    public void checkPlateClass(){
        if( !bus.getActivePlate().equals(bus.getOfficialPlate()) ){
            if( uiPlateLabel.getStyleClass().size() < 3 ){
                uiPlateLabel.getStyleClass().add("warning");
            }
        } else {
            try {
                uiPlateLabel.getStyleClass().remove(2);
            } catch( IndexOutOfBoundsException  e ){}
        }
    }

    public void updatePlateDownloadTimestamp(){
        uiPlateDataDownloadTimestampLabel.setText(Common.getCurrentHMin());
    }

    /**
     * Update header UI data
     */
    private void updateBaseData(){
        uiBusCodeLabel.setText(bus.getCode());
        uiRouteLabel.setText(bus.getRouteCode());
        uiPlateLabel.setText(bus.getActivePlate());
        checkPlateClass();
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



    /**
     * Subscribe the bus speed download event. Triggered from BusController.downloadSpeedData().
     *
     * @param event
     */
    @Subscribe
    private void subscribeSpeedDownloadFinishedEvent(BusSpeedDownloadFinishedEvent event){
        if( event.getBusCode().equals(bus.getCode())){
            uiSpeedLabel.setText(event.getSpeed() + "km/s");
        }
    }

}
