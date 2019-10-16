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
            JSONObject response = new JSONObject(APIRequest.POST(APIRequest.API_URL + "login", params.toString()));
            System.out.println(response);
            if( response.has("error") ){
                cb.onError(1);
            } else{
                cb.onSuccess();
            }
        });

    }

    private boolean validate(){
        return !(email.trim().equals("") || password.trim().equals(""));
    }

}
