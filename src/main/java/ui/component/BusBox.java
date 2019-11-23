/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import bots.BusFleetDataDownloader;
import controllers.BusController;
import interfaces.BusFleetDataDownloadListener;
import interfaces.MultipleActionCallback;
import models.Bus;
import repositories.BusStatusRepository;
import ui.UIComponent;
import utils.ThreadHelper;


public class BusBox extends UIComponent {

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

    /**
     * Constructor
     *
     * @param bus
     */
    public BusBox(Bus bus){
        this.bus = bus;
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

    public void downloadPlateData(){

    }

    public void downloadSpeedData(){

    }

    public void downloadMessageData(){

    }

    public void downloadIYSData(){

    }

    public void initUI(){
        loadFXML("bus_box_default");
        ((BusBoxController)getController()).setData(bus);
        ((BusBoxController)getController()).subscribeEvents(new MultipleActionCallback() {
            @Override
            public void onAction(int type) {
                switch(type){
                    case 0: // download fleet data trigger
                        triggerFleetDataAction(true);
                        break;
                    case 1: // download plate data trigger

                        break;
                }
            }
        });

        startDownloaders();
    }
}
