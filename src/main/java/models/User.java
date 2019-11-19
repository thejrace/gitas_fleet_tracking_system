/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import org.json.JSONException;
import org.json.JSONObject;

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

    /**
     * Getter for ID
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter for ID
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Getter for API Token
     *
     * @return
     */
    public String getApiToken() {
        return apiToken;
    }

    /**
     * Setter for API token
     *
     * @param apiToken
     */
    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    /**
     * Getter for email
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
