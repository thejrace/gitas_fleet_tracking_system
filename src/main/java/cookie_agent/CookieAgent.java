/*
 *  Kahya - Gitas 2019
 *
 *  Contributors:
 *      Ahmet Ziya Kanbur 2019-
 *
 * */
package cookie_agent;


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


        while( !checkCookie() ){

        }
        // get cookie accordingly
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
