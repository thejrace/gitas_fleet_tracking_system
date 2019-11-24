/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import bots.BusFleetDataDownloader;
import com.google.common.eventbus.Subscribe;
import controllers.BusController;
import events.bus_box.FleetDataDownloadEvent;
import events.bus_box.PlanPopupOpenEvent;
import events.bus_box.PlateUpdateEvent;
import interfaces.BusFleetDataDownloadListener;
import interfaces.Subscriber;
import javafx.stage.Stage;
import models.Bus;
import org.json.JSONObject;
import repositories.BusStatusRepository;
import ui.UIComponent;
import ui.popup_pages.BusPlanPopup;
import utils.GitasEventBus;
import utils.ThreadHelper;


public class BusBox extends UIComponent implements Subscriber {

    /**
     * Bus Model
     */
    private Bus bus;

    /**
     * Data download thread guard
     */
    private boolean activeFlag = true;

    /**
     * BusController instance
     */
    private BusController busController;

    private BusPlanPopup busPlanPopup;

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
            while( activeFlag ){

                triggerFleetDataAction(false);

                ThreadHelper.delay(50000); // @todo get from settings
            }
        });

    }

    /**
     * Download and process the fleet data
     */
    private void downloadAndProcessFleetData(){
        busController.downloadAndProcessFleetData(new BusFleetDataDownloadListener() {
            @Override
            public void onFinish(Bus updatedBus, BusFleetDataDownloader busFleetDataDownloader, BusStatusRepository busStatusRepository) {
                // update model
                bus = updatedBus;

                // update box
                ThreadHelper.runOnUIThread(() -> {
                    ((BusBoxController)getController()).setData(bus);
                    ((BusBoxController)getController()).setStatusData(
                            busStatusRepository.getStatus(),
                            busStatusRepository.getStatusLabel(),
                            busStatusRepository.getSubStatusLabel(),
                            busFleetDataDownloader.getRunStatusSummary());
                });
            }
            @Override
            public void onError(String error) {
                System.out.println(bus.getCode() + " --> " + error);
            }
        });
    }

    /**
     * Trigger method to execute downloadAndProcessFleetData
     *
     * @param async flag to determine the action will be handled in a thread
     */
    public void triggerFleetDataAction( boolean async ){
        if( async ){
            ThreadHelper.func(() -> {
                downloadAndProcessFleetData();
            });
        } else {
            downloadAndProcessFleetData();
        }
    }

    private void downloadPlateData(){
        busController.downloadPlateData((JSONObject data) -> {
            bus.updatePlates(data);
            ThreadHelper.runOnUIThread(() -> {
                ((BusBoxController)getController()).setData(bus);
                ((BusBoxController) getController()).updatePlateDownloadTimestamp();
            });
        });
    }

    public void triggerPlateDataAction( boolean async ){
        if( async ){
            ThreadHelper.func(() -> {
                downloadPlateData();
            });
        } else {
            downloadPlateData();
        }
    }

    public void downloadSpeedData(){

    }

    public void downloadMessageData(){

    }

    public void downloadIYSData(){

    }

    @Subscribe
    private void subscribePlateDataTriggerEvent(PlateUpdateEvent event) {
        final Bus busData = event.getBusData();
        if( busData.getCode().equals(bus.getCode()) ){
            bus.setOfficialPlate(busData.getOfficialPlate());
            bus.setActivePlate(busData.getActivePlate());
            triggerPlateDataAction(true);
        }
    }

    @Subscribe
    private void subscribeFleetDataTriggerEvent(FleetDataDownloadEvent event){
        final Bus busData = event.getBusData();
        if( busData.getCode().equals(bus.getCode()) ){
            triggerFleetDataAction(true);
        }
    }

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

    public void initUI(){
        loadFXML("bus_box_default");
        ((BusBoxController)getController()).setData(bus);

        startDownloaders();
    }
}
