/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import bots.BusFleetDataDownloader;
import bots.BusPlateDataDownloader;
import bots.BusSpeedDownloader;
import events.bus_box.BusPlateDataDownloadFinishedEvent;
import events.bus_box.BusSpeedDownloadFinishedEvent;
import events.bus_box.FleetDataDownloadFinishedEvent;
import interfaces.ActionCallback;
import interfaces.BusFleetDataDownloadListener;
import interfaces.BusPlateDataDownloadListener;
import models.Bus;
import org.json.JSONException;
import org.json.JSONObject;
import repositories.BusStatusRepository;
import utils.APIRequest;
import utils.GitasEventBus;
import utils.ThreadHelper;

import java.util.HashMap;
import java.util.Map;

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
     * BusPlateDataDownloader instance
     */
    private BusPlateDataDownloader plateDataDownloader;

    /**
     * BusSpeedDownloader instance
     */
    private BusSpeedDownloader busSpeedDownloader;

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
        // init plate download
        plateDataDownloader = new BusPlateDataDownloader(bus.getID());
        // init speed downloader
        busSpeedDownloader = new BusSpeedDownloader(bus.getCode());
    }

    /**
     * Downloads and process the fleet data and emits event
     */
    public void downloadFleetData(){
        fleetDataDownloader.action();
        if( !fleetDataDownloader.isErrorFlag() ){
            // update model's data
            bus.setRouteCode(fleetDataDownloader.getRouteCode());
            bus.setRunData(fleetDataDownloader.getRunData());

            // get status and alarms
            statusRepository.processRunData(bus, fleetDataDownloader.getRunStatusSummary(), fleetDataDownloader.getActiveRunIndex());

            // update filter flags
            bus.setFilterFlags(statusRepository.getFilterFlags());

            // update bus model in the fleet
            ControllerHub.FleetController.updateBus(bus);

            GitasEventBus.post(new FleetDataDownloadFinishedEvent(bus, statusRepository, fleetDataDownloader));
        }
    }

    /**
     * Download plate data and emit event
     */
    public void downloadPlateData(){
        plateDataDownloader.action();
        GitasEventBus.post(new BusPlateDataDownloadFinishedEvent(bus.getCode(), plateDataDownloader.getData()));
    }

    /**
     * Download speed data and emit event
     */
    public void downloadSpeedData(){
        busSpeedDownloader.action();
        if( !busSpeedDownloader.isErrorFlag() ){
            GitasEventBus.post(new BusSpeedDownloadFinishedEvent(bus.getCode(), busSpeedDownloader.getSpeed()));
        }
    }

    /**
     * Send request to API to change plate data of the given bus
     *
     * @param bus
     * @param cb
     */
    public static void updatePlateData(Bus bus, ActionCallback cb ){
        ThreadHelper.func( () -> {
            Map<String, String> params = new HashMap<>();
            params.put("active_plate", bus.getActivePlate());
            params.put("official_plate", bus.getOfficialPlate());
            try {
                JSONObject response = new JSONObject(APIRequest.POST(APIRequest.API_URL + "buses/"+bus.getID()+"/updatePlate", params));
                if( response.getJSONObject("data").getBoolean("success") ){
                    cb.onSuccess();
                }
            } catch( JSONException e ){
                cb.onError(0);
                e.printStackTrace();
            }
        });

    }
}
