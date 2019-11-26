/*
 *  Kahya - Gitas 2019
 *
 *  Contributors:
 *      Ahmet Ziya Kanbur 2019-
 *
 * */
package utils;

import controllers.ControllerHub;
import cookie_agent.CookieAgent;
import interfaces.ActionCallback;
import lombok.AllArgsConstructor;
import models.User;
import org.json.JSONException;
import org.json.JSONObject;

@AllArgsConstructor
public class LoginAttempt {

    /**
     * Email input
     */
    private String email;

    /**
     * Password input
     */
    private String password;

    /**
     * Login attempt @todo convert to event bus
     *
     * @param cb
     */
    public void commit(ActionCallback cb){
        if( !validate() ){
            cb.onError(0);
            return;
        }
        ThreadHelper.func(() -> {
            JSONObject params = new JSONObject();
            params.put("email", email);
            params.put("password", password);
            JSONObject response = new JSONObject(APIRequest.POST(APIRequest.API_URL + "login", params.toString()));
            System.out.println(response);
            if( response.has("error") ){
                cb.onError(1);
            } else{
                JSONObject data = response.getJSONObject("data");
                ControllerHub.UserController.setModel(new User(data));

                CookieAgent.FILO5_LOGIN = data.getString("filo5_login"); // @todo FIX THIS!!??
                CookieAgent.FILO5_PASS = data.getString("filo5_pass");

                ControllerHub.UserController.updateStaticConfigAfterLogin();
                cb.onSuccess();
            }
        });
    }

    /**
     * Validates the inputs
     *
     * @return
     */
    private boolean validate(){
        return !(email.trim().equals("") || password.trim().equals(""));
    }
}
