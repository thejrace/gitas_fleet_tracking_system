/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import interfaces.ActionCallback;
import models.User;
import org.json.JSONException;
import org.json.JSONObject;
import utils.APIRequest;
import utils.SharedConfig;

public class UserController {

    /**
     * User model
     */
    private User model;

    /**
     * Empty constructor
     */
    public UserController(){
        this.model = new User(0, "", "", "");
    }

    /**
     * Send a request to API to validate and get the data of the user using it's api token
     *
     * @param callback
     */
    public void remember(ActionCallback callback){
        try {
            // read api token from static config file
            model.setApiToken(SharedConfig.DATA.getString("api_key"));
            JSONObject response = new JSONObject(APIRequest.POST(APIRequest.API_URL + "fts/rememberMe", ""));
            JSONObject data = response.getJSONObject("data");
            model.batchUpdate(data);
            callback.onSuccess();
        } catch( JSONException e ){
            System.out.println("INVALID LOGIN");
            callback.onError(0);
            e.printStackTrace();
        }
    }

    /**
     * Update the static config data after login with form action
     */
    public void updateStaticConfigAfterLogin(){
        SharedConfig.updateStaticConfigToRememberUser(getModel().getApiToken());
    }

    /**
     * Logout from session
     */
    public void logout(){
        SharedConfig.resetStaticConfigToForgetUser();
        model = null;
    }

    /**
     * Getter for the model
     *
     * @return
     */
    public User getModel(){
        return model;
    }

    /**
     * Setter for the model
     *
     * @param model
     */
    public void setModel( User model ){
        this.model = model;
    }

}
