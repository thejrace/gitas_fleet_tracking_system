package ui.block;

import interfaces.ActionCallback;
import ui.UIComponent;

public class CaptchaWebview extends UIComponent {

    public CaptchaWebview(){

    }

    public void setListener(ActionCallback listener){
        ((CaptchaWebviewController)(getController())).setListener(listener);
    }

    public void initUI(){
        loadFXML("captcha_webview");
    }
}
