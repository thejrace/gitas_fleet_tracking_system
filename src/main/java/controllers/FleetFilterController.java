/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import enums.FleetFilterButtonAction;
import ui.page.FleetPage;

import java.util.ArrayList;

public class FleetFilterController {

    /**
     * Active filter states
     */
    private ArrayList<Boolean> filterStates;

    /**
     * Active style choice
     */
    private int styleChoice;

    /**
     * Empty constructor
     */
    public FleetFilterController(){
        filterStates = new ArrayList<>();
        filterStates.add(false);
        filterStates.add(false);
        filterStates.add(false);
        styleChoice = 0;
    }

    /**
     * Updates the active filter states
     *
     * @param action
     * @param newState
     */
    public void updateFilterStates(FleetFilterButtonAction action, boolean newState ){
        filterStates.set(action.ordinal(), newState);
        FleetPage.FleetController.applyFilters();
    }

    /**
     * Updates the active style choice
     *
     * @param newChoice
     */
    public void updateStyleChoice( int newChoice ){
        this.styleChoice = newChoice;
        FleetPage.FleetController.applyStyles();
    }

    /**
     * Setter for filter states. ( User defined import )
     *
     * @param filterStates
     */
    public void setFilterStates(ArrayList<Boolean> filterStates) {
        this.filterStates = filterStates;
    }

    /**
     * Getter for filter states
     *
     * @return
     */
    public ArrayList<Boolean> getFilterStates() {
        return filterStates;
    }

    /**
     * Getter for active style choice
     *
     * @return
     */
    public int getStyleChoice() {
        return styleChoice;
    }
}
