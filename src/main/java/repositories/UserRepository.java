/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package repositories;

import org.json.JSONObject;
import utils.APIRequest;

public class UserRepository {

    /**
     * Validate user's api token and return it's data from API
     *
     * @return user data
     */
    public static JSONObject remember(){
        JSONObject response = new JSONObject(APIRequest.POST(APIRequest.API_URL + "fts/rememberMe", ""));
        return response.getJSONObject("data");
    }

}
