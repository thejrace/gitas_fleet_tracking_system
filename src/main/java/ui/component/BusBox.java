/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import com.google.common.eventbus.Subscribe;
import controllers.BusController;
import events.bus_box.*;
import interfaces.Subscriber;
import javafx.stage.Stage;
import models.Bus;
import ui.UIComponent;
import ui.popup_pages.BusDriversPopup;
import ui.popup_pages.BusMessagesPopup;
import ui.popup_pages.BusPlanPopup;
import utils.GitasEventBus;
import utils.ThreadHelper;

public class BusBox extends UIComponent implements Subscriber {

    /**
     * Bus Model
     */
    private Bus bus;

    /**
     * Fleet data download thread guard
     */
    private boolean fleetDataDownloadFlag = true;

    /**
     * Speed data download thread guard
     */
    private boolean speedDataDownloadFlag = true;

    /**
     * PDKS data download thread guard
     */
    private boolean pdksDataDownloadFlag = true;

    /**
     * BusController instance
     */
    private BusController busController;

    /**
     * BusPlanPopup instance
     */
    private BusPlanPopup busPlanPopup;

    /**
     * BusDriversPopup instance
     */
    private BusDriversPopup busDriversPopup;

    /**
     * BusMessagesPopup instance
     */
    private BusMessagesPopup busMessagesPopup;
    /**
     * Constructor
     *
     * @param bus
     */
    public BusBox(Bus bus){
        this.bus = bus;
        GitasEventBus.register(this);
    }

    /**
     * Initialize downloader bots
     */
    public void startDownloaders(){

        busController = new BusController(bus);
        busController.initialize();

        ThreadHelper.func( () -> {
            while( fleetDataDownloadFlag ){
                downloadFleetData(false);
                ThreadHelper.delay(50000); // @todo get from settings
            }
        });

        ThreadHelper.func(() -> {
            while( speedDataDownloadFlag ){
                downloadSpeedData(false);
                ThreadHelper.delay(50000); // @todo get from settings
            }
        });

        ThreadHelper.func(() -> {
            // for PDKS download, we wait for a while to FleetDataDownload action to finish
            // we need rundata to inject driver names
            ThreadHelper.delay(10000); // @todo get from settings

            while( pdksDataDownloadFlag ){
                downloadPDKSData(false);
                ThreadHelper.delay(15 * 60000 ); // @todo get from settings
            }
        });

    }

    /**
     * Trigger method to execute downloadAndProcessFleetData
     *
     * @param async flag to determine the action will be handled in a thread
     */
    public void downloadFleetData( boolean async ){
        if( async ){
            ThreadHelper.func(() -> {
                busController.downloadFleetData();
            });
        } else {
            busController.downloadFleetData();
        }
    }

    /**
     * Downloads plate data
     */
    private void downloadPlateData(boolean async){
        if( async ){
            ThreadHelper.func(() -> {
                busController.downloadPlateData();
            });
        } else {
            busController.downloadPlateData();
        }
    }

    /**
     * Downloads speed data
     */
    private void downloadSpeedData(boolean async){
        if( async ){
            ThreadHelper.func(() -> {
                busController.downloadSpeedData();
            });
        } else {
            busController.downloadSpeedData();
        }
    }

    /**
     * Trigger method to execute downloadPDKSData
     *
     * @param async flag to determine the action will be handled in a thread
     */
    public void downloadPDKSData( boolean async ){
        if( async ){
            ThreadHelper.func(() -> {
                busController.downloadPDKSData();
            });
        } else {
            busController.downloadPDKSData();
        }
    }

    /**
     * Subscribe the fleet data download trigger event. Triggered from BusBoxController.
     *
     * @param event
     */
    @Subscribe
    private void subscribeFleetDataTriggerEvent(FleetDataDownloadEvent event){
        final Bus busData = event.getBusData();
        if( busData.getCode().equals(bus.getCode()) ){
            downloadFleetData(true);
        }
    }

    /**
     * Subscribe fleet data download finished event. Triggered from BusController.downloadFleetData().
     *
     * @param event
     */
    @Subscribe
    private void subscribeFleetDataDownloadFinishedEvent(FleetDataDownloadFinishedEvent event){
        if( bus.getCode().equals(event.getBus().getCode())){
            bus = event.getBus();
            ThreadHelper.runOnUIThread( () -> {
                ((BusBoxController)getController()).setData(bus);
                ((BusBoxController)getController()).setStatusData(
                        event.getBusStatusRepository().getStatus(),
                        event.getBusStatusRepository().getStatusLabel(),
                        event.getBusStatusRepository().getSubStatusLabel(),
                        event.getBusFleetDataDownloader().getRunStatusSummary());
            });
        }
    }

    /**
     * Subscribe plate data download finished event. Triggered from BusController.downloadPlateData().
     *
     * @param event
     */
    @Subscribe
    private void subscribePlateDataDownloadFinishedFlag(BusPlateDataDownloadFinishedEvent event){
        if( bus.getCode().equals(event.getBusCode())){
            bus.updatePlates(event.getNewData());
            ThreadHelper.runOnUIThread(() -> {
                ((BusBoxController)getController()).setData(bus);
                ((BusBoxController) getController()).updatePlateDownloadTimestamp();
            });
        }
    }

    /**
     * Subscribe the plate data update event. Triggered from BusPlateFormPopupController
     *
     * @param event
     */
    @Subscribe
    private void subscribePlateDataTriggerEvent(PlateUpdateEvent event) {
        final Bus busData = event.getBusData();
        if( busData.getCode().equals(bus.getCode()) ){
            bus.setOfficialPlate(busData.getOfficialPlate());
            bus.setActivePlate(busData.getActivePlate());
            downloadPlateData(true);
        }
    }


    /**
     * Subscribe the bus speed download event. Triggered from BusController.downloadSpeedData().
     *
     * @param event
     */
    @Subscribe
    private void subscribeSpeedDownloadFinishedEvent(BusSpeedDownloadFinishedEvent event){
        if( event.getBusCode().equals(bus.getCode())){
            ThreadHelper.runOnUIThread(() -> {
                ((BusBoxController)getController()).setSpeedData(event.getSpeed());
            });
        }
    }

    /**
     * Subscribe the BusPlanPopup opener event. Triggered from BusBoxButton click event.
     *
     * @param event
     */
    @Subscribe
    private void subscribePlanPopupOpenEvent(PlanPopupOpenEvent event){
        final Bus busData = event.getBusData();
        if( busData.getCode().equals(bus.getCode()) ){
            try {
                // @todo check if opened before
                if( busPlanPopup == null ){
                    busPlanPopup = new BusPlanPopup();
                }
                try {
                    busPlanPopup.start( new Stage() );
                    busPlanPopup.setData(bus);
                } catch( Exception e ){
                    e.printStackTrace();
                }
            } catch ( Exception e ){
                e.printStackTrace();
            }
        }
    }

    /**
     * Subscribe the BusMessagePopupOpened event. Triggered from BusBoxButton click event.
     */
    @Subscribe
    private void subscribeBusMessagesPopupOpenedEvent(BusMessagesPopupOpenedEvent event){
        final Bus busData = event.getBusData();
        if( busData.getCode().equals(bus.getCode()) ){
            try {
                // @todo check if opened before
                if( busMessagesPopup == null ){
                    busMessagesPopup = new BusMessagesPopup();
                }
                try {
                    busMessagesPopup.start( new Stage() );
                    busMessagesPopup.setData(bus);

                    ThreadHelper.func(() -> {
                        // download data
                        busController.downloadMessagesData();
                    });
                } catch( Exception e ){
                    e.printStackTrace();
                }
            } catch ( Exception e ){
                e.printStackTrace();
            }
        }
    }

    /**
     * Subscribe the BusDriverPopupOpened event. Triggered from BusBoxButton click event.
     */
    @Subscribe
    private void subscribeBusDriversPopupOpenedEvent(BusDriversPopupOpenedEvent event){
        final Bus busData = event.getBusData();
        if( busData.getCode().equals(bus.getCode()) ){
            try {
                // @todo check if opened before
                if( busDriversPopup == null ){
                    busDriversPopup = new BusDriversPopup();
                }
                try {
                    busDriversPopup.start( new Stage() );
                    busDriversPopup.setData(bus);

                    ThreadHelper.func(() -> {
                        // download data
                        busController.downloadDriversData();
                    });
                } catch( Exception e ){
                    e.printStackTrace();
                }
            } catch ( Exception e ){
                e.printStackTrace();
            }
        }
    }

    /**
     * Initialize FXML and controller
     */
    public void initUI(){
        loadFXML("bus_box_default");
        ((BusBoxController)getController()).setData(bus);

        startDownloaders();
    }
}
