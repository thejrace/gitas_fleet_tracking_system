/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import models.Bus;
import repositories.BusRepository;

import java.util.ArrayList;

public class FleetController {

    /**
     * List of buses
     */
    private ArrayList<Bus> buses;

    /**
     * Counter for the buses array
     */
    private int indexCounter = 0;

    /**
     * BusRepository instance
     */
    private BusRepository busRepository;

    /**
     * Empty constructor
     */
    public FleetController(){
        buses = new ArrayList<>();
        busRepository = new BusRepository();
    }

    /**
     * Add new bus to the fleet
     * @param bus
     */
    private void addBus(Bus bus){
        bus.setIndex(indexCounter);
        buses.add(bus);
        indexCounter++;
    }

    /**
     * Download the user's buses
     */
    public void downloadBuses(){
        // get buses
        busRepository.fetchBuses();

        // create bus instances
        for( int k = 0; k < busRepository.getData().length(); k++ ){
            addBus(new Bus(busRepository.getData().getJSONObject(k)));
        }
    }

    /**
     * Return the fleet data
     *
     * @return
     */
    public ArrayList<Bus> getBuses(){
        return buses;
    }

}
