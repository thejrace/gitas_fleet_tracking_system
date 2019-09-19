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


public class BusRepository {

    private JSONArray data = new JSONArray();

    public BusRepository(){

    }

    public void fetchBuses(){
        APIRequest.API_TOKEN = "wPpN8i1RiNmRMqRcnWeMND9YlSX69MUca2gBkhLxpTyrx0PcC66EeJCElCyn";
        JSONObject result = new JSONObject(APIRequest.GET("http://gitfilo.com/api/buses"));
        data = result.getJSONArray("data");
    }

    public JSONArray getData(){
        return data;
    }

}

