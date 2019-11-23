/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import enums.FleetFilterButtonAction;
import org.json.JSONException;
import org.json.JSONObject;
import ui.component.BusBox;

import java.util.ArrayList;
import java.util.Map;

public class Bus {

    /**
     * Code of the bus
     */
    private String code;

    /**
     * Backend ID of the bus
     */
    private int ID;

    /**
     * Official plate of the bus
     */
    private String officialPlate;

    /**
     * Active plate of the bus
     */
    private String activePlate;

    /**
     * Index of the bus in the FleetController.buses array
     */
    private int index;

    /**
     * Route code of the bus
     */
    private String routeCode;

    /**
     * Active run data
     */
    private ArrayList<BusRun> runData;

    /**
     * Filter related flags list
     */
    private Map<FleetFilterButtonAction, Boolean> filterFlags;

    /**
     * UI Component
     */
    private BusBox uiComponent;

    /**
     * JSON constructor
     *
     * @param data
     */
    public Bus(JSONObject data){
        try {
            this.code = data.getString("code");
            this.officialPlate = data.getString("official_plate");
            this.activePlate = data.getString("active_plate");
            this.ID = data.getInt("id");
            this.uiComponent = new BusBox(this);
            this.uiComponent.initUI();
        } catch( JSONException e ){
            e.printStackTrace();
        }
    }

    /**
     * Getter for the code
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for the code
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for officialPlate
     *
     * @return
     */
    public String getOfficialPlate() {
        return officialPlate;
    }

    /**
     * Setter for officialPlate
     *
     * @param officialPlate
     */
    public void setOfficialPlate(String officialPlate) {
        this.officialPlate = officialPlate;
    }

    /**
     * Getter for activePlate
     *
     * @return
     */
    public String getActivePlate() {
        return activePlate;
    }

    /**
     * Setter for activePlate
     *
     * @param activePlate
     */
    public void setActivePlate(String activePlate) {
        this.activePlate = activePlate;
    }

    /**
     * Getter for index
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter for index
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Getter for runData
     *
     * @return
     */
    public ArrayList<BusRun> getRunData() {
        return runData;
    }

    /**
     * Setter for runData
     *
     * @param runData
     */
    public void setRunData(ArrayList<BusRun> runData) {
        this.runData = runData;
    }

    /**
     * Getter for UI component
     *
     * @return
     */
    public BusBox getUiComponent() {
        return uiComponent;
    }

    /**
     * Setter for the UI component
     *
     * @param uiComponent
     */
    public void setUiComponent(BusBox uiComponent) {
        this.uiComponent = uiComponent;
    }

    /**
     * Getter for routeCode
     *
     * @return
     */
    public String getRouteCode() {
        return routeCode;
    }

    /**
     * Setter for routeCode
     *
     * @param routeCode
     */
    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    /**
     * Getter for filterFlags
     *
     * @return
     */
    public Map<FleetFilterButtonAction, Boolean> getFilterFlags() {
        return filterFlags;
    }

    /**
     * Setter for filterFlags
     *
     * @param filterFlags
     */
    public void setFilterFlags(Map<FleetFilterButtonAction, Boolean> filterFlags) {
        this.filterFlags = filterFlags;
    }
}
