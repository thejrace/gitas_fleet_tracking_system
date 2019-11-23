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
import interfaces.ActionCallback;
import interfaces.BusFleetDataDownloadListener;
import interfaces.BusPlateDataDownloadListener;
import models.Bus;
import org.json.JSONException;
import org.json.JSONObject;
import repositories.BusStatusRepository;
import utils.APIRequest;
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
    }

    /**
     * Downloads fleet data and process' it for alarms and status updates
     *
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

            // update filter flags
            bus.setFilterFlags(statusRepository.getFilterFlags());

            // update bus model in the fleet
            ControllerHub.FleetController.updateBus(bus);

            // notify busbox
            listener.onFinish(bus, fleetDataDownloader, statusRepository);
        } else {
            listener.onError("Error!");
        }
    }

    public void downloadPlateData(BusPlateDataDownloadListener listener){
        plateDataDownloader.action();
        listener.onFinish(plateDataDownloader.getData());
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

    public void updatePlateData(String busCode){

    }

}
