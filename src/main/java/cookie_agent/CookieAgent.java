/*
 *  Kahya - Gitas 2019
 *
 *  Contributors:
 *      Ahmet Ziya Kanbur 2019-
 *
 * */
package cookie_agent;


import enums.DataSourceSettings;
import interfaces.ActionCallback;
import javafx.application.Platform;
import ui.block.CaptchaWebview;
import ui.popup.Popup;
import utils.SharedConfig;
import utils.ThreadHelper;

public class CookieAgent {
    /**
     * Shared cookie
     */
    public static String FILO5_COOKIE;

    /**
     * Status flag
     */
    public static boolean READY = false;

    public static String LOGIN = "dk_oasfilo";
    public static String PASS = "1453.oas";

    public static void initialize(){
        // check user settings for data source
        int source = SharedConfig.SETTINGS.getInt("data_source");

        if( source == DataSourceSettings.FLEET.ordinal() ){
            getCookieFromFleet();
        } else {
            getCookieFromServer();
        }

        while( !checkCookie() ){

            ThreadHelper.delay(10000);
            break;
        }


    }

    private static void getCookieFromFleet() {
        // get fleet credentials
        CaptchaWebview captchaWebview = new CaptchaWebview();
        captchaWebview.initUI();
        captchaWebview.setListener(new ActionCallback() {
            @Override
            public void onSuccess(String... params) {
                System.out.println("SUCCESS!");
                System.out.println(CookieAgent.FILO5_COOKIE);
                Platform.runLater(() -> {
                    // show loader again for bus fetch action
                    //Popup.showLoader();
                });
            }

            @Override
            public void onError(int type) {
                System.out.println("ERROR!!");
            }
        });

        Platform.runLater(() -> {
            Popup.setContent(captchaWebview.getUI());
        });

        // we will start captcha screen, embed it to popup


    }

    private static void getCookieFromServer() {
        // get cookie from api


    }

    private static boolean checkCookie(){ // @todo
        return false;
    }

    /**
     * Getter for READY flag
     *
     * @return ready flag
     */
    public static boolean isReady(){
        return READY;
    }
}
