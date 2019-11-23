/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import models.Bus;
import repositories.FleetRepository;
import ui.page.FleetPage;

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
     * FleetRepository instance
     */
    private FleetRepository fleetRepository;

    /**
     * UI page
     */
    private FleetPage fleetPage;

    /**
     * Empty constructor
     */
    public FleetController(){
        buses = new ArrayList<>();
        fleetRepository = new FleetRepository();
    }

    /**
     * Notify FleetPage to apply filters.
     */
    public void applyFilters(){
        fleetPage.applyFilters();
    }

    /**
     * Loop through buses and apply new style choice
     */
    public void applyStyles(){
        fleetPage.applyStyles();
    }

    /**
     * Add new bus to the fleet
     *
     * @param bus
     */
    private void addBus(Bus bus){
        bus.setIndex(indexCounter);
        buses.add(bus);
        indexCounter++;
    }

    /**
     * Replace updated model with old one
     *
     * @param bus
     */
    public void updateBus(Bus bus) {
        buses.set(bus.getIndex(), bus);
        System.out.println(buses);
    }

    /**
     * Download the user's buses
     */
    public void downloadBuses(){
        // get buses
        fleetRepository.fetchBuses();

        // create bus instances
        for( int k = 0; k < fleetRepository.getData().length(); k++ ){
            addBus(new Bus(fleetRepository.getData().getJSONObject(k)));
        }
    }

    /**
     * Get fleetPage instance
     *
     * @param fleetPage
     */
    public void passFleetPage(FleetPage fleetPage){
        this.fleetPage = fleetPage;
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
