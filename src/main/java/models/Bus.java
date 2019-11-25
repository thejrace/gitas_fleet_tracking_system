/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import enums.FleetFilterButtonAction;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;
import ui.component.BusBox;

import java.util.ArrayList;
import java.util.Map;

@Data
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
     * Updates only the plates
     *
     * @param data
     */
    public void updatePlates(JSONObject data){
        try {
            this.officialPlate = data.getString("official_plate");
            this.activePlate = data.getString("active_plate");
        } catch( JSONException e ){
            e.printStackTrace();
        }
    }

    /**
     * Returns the last ORER of the bus. Used in RunSuggestions
     *
     * @return
     */
    public String getLastORER(){
        try {
            return getRunData().get(runData.size()-1).getORER();
        } catch( ArrayIndexOutOfBoundsException e ){}
        return "";
    }

    /**
     * Returns the last RouteDetails string of the bus. Used in RunSuggestions
     *
     * @return
     */
    public String getLastRouteDetails() {
        try {
            return getRunData().get(runData.size()-1).getRouteDetails();
        } catch( ArrayIndexOutOfBoundsException e ){}
        return "";
    }
}
