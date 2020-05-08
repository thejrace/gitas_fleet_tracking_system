/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;
import utils.APIRequest;

public class BusPlateDataDownloader {

    /**
     * Backend ID of the bus
     */
    private int busID;

    /**
     * Output data
     */
    @Getter
    private JSONObject data;

    /**
     * Constructor
     *
     * @param busID
     */
    public BusPlateDataDownloader(int busID){
        this.busID = busID;
    }

    /**
     * Downloads the plate data from API
     */
    public void action(){
        JSONObject result = new JSONObject(APIRequest.GET(APIRequest.API_URL + "buses/"+busID));
        try {
            data = result.getJSONObject("data");
        } catch( JSONException e ){
            e.printStackTrace();
        }
    }
}
