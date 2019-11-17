package utils;

import interfaces.ActionCallback;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginAttempt {

    private String email;

    private String password;

    public LoginAttempt( String email, String password ){
        this.email = email;
        this.password = password;
    }

    public void commit(ActionCallback cb){
        if( !validate() ){
            cb.onError(0);
            return;
        }
        ThreadHelper.func(() -> {
            JSONObject params = new JSONObject();
            params.put("email", email);
            params.put("password", password);
            System.out.println(APIRequest.API_URL);
            JSONObject response = new JSONObject(APIRequest.POST(APIRequest.API_URL + "login", params.toString()));
            System.out.println(response);
            if( response.has("error") ){
                cb.onError(1);
            } else{
                APIRequest.API_TOKEN = response.getJSONObject("data").getString("api_token");
                SharedConfig.USER_ID = response.getJSONObject("data").getInt("user_id");
                SharedConfig.updateStaticConfigToRememberUser();
                cb.onSuccess();
            }
        });

    }

    private boolean validate(){
        return !(email.trim().equals("") || password.trim().equals(""));
    }

}
