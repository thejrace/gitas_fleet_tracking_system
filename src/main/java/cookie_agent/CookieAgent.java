/*
 *  Kahya - Gitas 2019
 *
 *  Contributors:
 *      Ahmet Ziya Kanbur 2019-
 *
 * */
package cookie_agent;


import enums.DataSourceSettings;
import utils.SharedConfig;

public class CookieAgent {
    /**
     * Shared cookie
     */
    public static String FILO5_COOKIE;

    /**
     * Status flag
     */
    public static boolean READY = false;

    public static void initialize(){
        // check user settings for data source
        int source = SharedConfig.SETTINGS.getInt("data_source");

        if( source == DataSourceSettings.FLEET.ordinal() ){
            getCookieFromFleet();
        } else {
            getCookieFromServer();
        }

        while( !checkCookie() ){

        }
        // get cookie accordingly
    }

    private static void getCookieFromFleet() {
        // we will start captcha screen, embed it to popup




    }

    private static void getCookieFromServer() {
        // get cookie from api


    }

    private static boolean checkCookie(){ // @todo
        return true;
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
