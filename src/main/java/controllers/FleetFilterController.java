/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import enums.FleetFilterButtonAction;

import java.util.HashMap;
import java.util.Map;

public class FleetFilterController {

    /**
     * Active filter states
     */
    private Map<FleetFilterButtonAction, Boolean> filterStates;

    /**
     * Active style choice
     */
    private int styleChoice;

    /**
     * Empty constructor
     */
    public FleetFilterController(){
        filterStates = new HashMap<>();
        filterStates.put(FleetFilterButtonAction.ACTIVE, false);
        filterStates.put(FleetFilterButtonAction.ZAYI, false);
        filterStates.put(FleetFilterButtonAction.PLATE, false);
        styleChoice = 0;
    }

    /**
     * Updates the active filter states
     *
     * @param action
     * @param newState
     */
    public void updateFilterStates(FleetFilterButtonAction action, boolean newState ){
        filterStates.put(action, newState);
        ControllerHub.FleetController.applyFilters();
    }

    /**
     * Updates the active style choice
     *
     * @param newChoice
     */
    public void updateStyleChoice( int newChoice ){
        this.styleChoice = newChoice;
        ControllerHub.FleetController.applyStyles();
    }

    /**
     * Setter for filter states. ( User defined import )
     *
     * @param filterStates
     */
    public void setFilterStates(Map<FleetFilterButtonAction, Boolean> filterStates) {
        this.filterStates = filterStates;
    }

    /**
     * Getter for filter states
     *
     * @return
     */
    public Map<FleetFilterButtonAction, Boolean> getFilterStates() {
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
