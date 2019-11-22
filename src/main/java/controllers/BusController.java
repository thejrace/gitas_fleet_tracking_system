/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import bots.BusFleetDataDownloader;
import interfaces.BusFleetDataDownloadListener;
import models.Bus;
import repositories.BusStatusRepository;

public class BusController {

    /**
     * Bus Model
     */
    private Bus bus;

    /**
     * BusFleetDataDownloader instance
     */
    private BusFleetDataDownloader fleetDataDownloader;

    /**
     * BusStatusRepository instance
     */
    private BusStatusRepository statusRepository;

    /**
     * Constructor
     *
     * @param bus
     */
    public BusController(Bus bus){
        this.bus = bus;
    }

    /**
     * Initializes the member objects
     */
    public void initialize(){
        // init downloader
        fleetDataDownloader = new BusFleetDataDownloader(bus.getCode());
        // init repository
        statusRepository = new BusStatusRepository(bus.getCode());
    }

    /**
     * Downloads fleet data and process' it for alarms and status updates
     * @param listener
     */
    public void downloadAndProcessFleetData( BusFleetDataDownloadListener listener ){
        fleetDataDownloader.action();
        if( !fleetDataDownloader.getErrorFlag() ){
            // update model's data
            bus.setRouteCode(fleetDataDownloader.getRouteCode());
            bus.setRunData(fleetDataDownloader.getRunData());

            // get status and alarms
            statusRepository.processRunData(bus, fleetDataDownloader.getRunStatusSummary(), fleetDataDownloader.getActiveRunIndex());

            // notify busbox
            listener.onFinish(bus, fleetDataDownloader, statusRepository);
        } else {
            listener.onError("Error!");
        }
    }

    public void downloadMessages(String busCode){

    }

    public void downloadIYSRecords(String busCode){

    }

    public void downloadNotes(String busCode){

    }

    public void downloadReports(String busCode){

    }

    public void downloadDriverData(String busCode){

    }

    public void downloadSpeedRecords(String busCode){

    }

    public void downloadPlateData(String busCode){

    }

    public void updatePlateData(String busCode){

    }

}
