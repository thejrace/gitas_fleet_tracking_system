/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package repositories;

import controllers.ControllerHub;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.APIRequest;

public class FleetRepository {

    @Getter
    private JSONArray data = new JSONArray();

    public FleetRepository(){

    }

    public void fetchBuses(){
        JSONObject result = new JSONObject(APIRequest.GET(APIRequest.API_URL + "users/" + ControllerHub.UserController.getModel().getID() + "/buses"));
        data = result.getJSONArray("data");
        System.out.println(data);
    }
}

