/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package repository;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.APIRequest;
import utils.SharedConfig;


public class BusRepository {

    private JSONArray data = new JSONArray();

    public BusRepository(){

    }

    public void fetchBuses(){
        JSONObject result = new JSONObject(APIRequest.GET(APIRequest.API_URL + "users/"+ SharedConfig.USER_ID+"/buses"));
        data = result.getJSONArray("data");
        System.out.println(data);
    }

    public JSONArray getData(){
        return data;
    }

}

