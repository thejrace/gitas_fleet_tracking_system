/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package repositories;

import controllers.ControllerHub;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.APIRequest;

public class BusRepository {

    private JSONArray data = new JSONArray();

    public BusRepository(){

    }

    public void fetchBuses(){
        JSONObject result = new JSONObject(APIRequest.GET(APIRequest.API_URL + "users/" + ControllerHub.UserController.getModel().getID() + "/buses"));
        data = result.getJSONArray("data");
        System.out.println(data);
    }

    public JSONArray getData(){
        return data;
    }

}

