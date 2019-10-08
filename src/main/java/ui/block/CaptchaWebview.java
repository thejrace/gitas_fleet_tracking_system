package ui.block;

import interfaces.ActionCallback;
import ui.UIComponent;

public class CaptchaWebview extends UIComponent {

    /**
     * Constructor
     */
    public CaptchaWebview(){

    }

    /**
     * Set listener to controller.
     *
     * @param listener
     */
    public void setListener(ActionCallback listener){
        ((CaptchaWebviewController)(getController())).setListener(listener);
    }

    /**
     * Pass notf message to the controller.
     * @param notfText
     */
    public void setNotf(String notfText){
        ((CaptchaWebviewController)(getController())).setNotf(notfText);
    }


    public void initUI(){
        loadFXML("captcha_webview");
    }
}
