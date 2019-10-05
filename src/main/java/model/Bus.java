/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package model;

import org.json.JSONException;
import org.json.JSONObject;
import ui.component.BusBox;

import java.util.ArrayList;

public class Bus {

    private String code;
    private String officialPlate;
    private String activePlate;

    private BusBox box;

    private ArrayList<BusRun> runData;

    public Bus(JSONObject data){
        try {
            this.code = data.getString("code");
            this.officialPlate = data.getString("official_plate");
            this.activePlate = data.getString("active_plate");
            box = new BusBox(this);
        } catch( JSONException e ){
            e.printStackTrace();
        }
    }

    public Bus(String code, String officialPlate, String activePlate ){
        this.code = code;
        this.officialPlate = officialPlate;
        this.activePlate = activePlate;
    }

    private void downloadData(){

    }

    public BusBox getBox() {
        return box;
    }

    public void setBox(BusBox box) {
        this.box = box;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOfficialPlate() {
        return officialPlate;
    }

    public void setOfficialPlate(String officialPlate) {
        this.officialPlate = officialPlate;
    }

    public String getActivePlate() {
        return activePlate;
    }

    public void setActivePlate(String activePlate) {
        this.activePlate = activePlate;
    }

}
