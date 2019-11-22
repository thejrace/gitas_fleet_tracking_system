/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import bots.BusFleetDataDownloader;
import models.Bus;
import repositories.BusStatusRepository;
import ui.UIComponent;
import utils.ThreadHelper;

import java.util.HashMap;

public class BusBox extends UIComponent {

    private Bus bus;

    private boolean activeFlag = true;

    private BusFleetDataDownloader fleetDataDownloader;

    private BusStatusRepository statusRepository;

    public BusBox(Bus bus){
        this.bus = bus;
    }

    public void startDownloaders(){

        // we will start with fleet data download action
        fleetDataDownloader = new BusFleetDataDownloader(bus.getCode());

        statusRepository = new BusStatusRepository(bus.getCode());

        ThreadHelper.func( () -> {
            while( activeFlag ){

                fleetDataDownloader.action();

                if( !fleetDataDownloader.getErrorFlag() ){

                    bus.setRouteCode(fleetDataDownloader.getRouteCode());
                    bus.setRunData(fleetDataDownloader.getRunData());

                    statusRepository.processRunData(bus, fleetDataDownloader.getRunStatusSummary(), fleetDataDownloader.getActiveRunIndex());

                    ((BusBoxController)getController()).setData(bus, fleetDataDownloader.getRunStatusSummary());
                    ((BusBoxController)getController()).setStatusData(statusRepository.getStatus(), statusRepository.getStatusLabel(), statusRepository.getSubStatusLabel());

                }

                ThreadHelper.delay(50000);
            }
        });


    }

    public void initUI(){
        loadFXML("bus_box_default");
        ((BusBoxController)getController()).setData(bus, new HashMap<>());

        startDownloaders();
    }
}
