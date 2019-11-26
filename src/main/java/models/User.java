/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

@Data
public class User {

    /**
     * ID of the user
     */
    private int ID;

    /**
     * API Token of the user
     */
    private String apiToken;

    /**
     * Email of the user
     */
    private String email;

    /**
     * Name of the user
     */
    private String name;

    /**
     * Json Constructor
     *
     * @param userData
     */
    public User( JSONObject userData ){
        jsonReplace(userData);
    }

    /**
     * Full constructor
     *
     * @param ID
     * @param email
     * @param name
     * @param apiToken
     */
    public User( int ID, String email, String name, String apiToken ){
        this.ID = ID;
        this.email = email;
        this.name = name;
        this.apiToken = apiToken;
    }

    /**
     * Empty constructor
     */
    public User(){

    }

    /**
     * Update properties with given JSONObject
     *
     * @param data
     */
    public void batchUpdate(JSONObject data){
        jsonReplace(data);
    }

    /**
     * Replace properties with given json data
     *
     * @param data
     */
    private void jsonReplace(JSONObject data){
        try {
            this.ID = data.getInt("user_id");
            this.email = data.getString("email");
            this.name = data.getString("name");
            this.apiToken = data.getString("api_token");
        } catch( JSONException e ){
            e.printStackTrace();
        }
    }
}
