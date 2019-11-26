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
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ui.block.CaptchaWebview;
import ui.popup.Popup;
import utils.SharedConfig;
import utils.ThreadHelper;

import java.io.IOException;

public class CookieAgent {

    /**
     * Shared cookie
     */
    public static String FILO5_COOKIE;

    /**
     * Status flag
     */
    public static boolean ReadyToCheckCookieFlag = false;

    /**
     * Login credentials
     */
    public static String FILO5_LOGIN = "";
    public static String FILO5_PASS = "";

    /**
     * Cookie server urls @todo will get from shared config?
     */
    public static String[] COOKIE_SERVER_URLS = {
        "http://192.168.2.177/filotakip/get_cookie?key=nJAHJjksd13",
        "http://gitsistem.com/filotakip/get_cookie?key=nJAHJjksd13"
    };

    /**
     * Initialize cookie agent
     */
    public static void initialize(){
        // check user settings for data source
        int source = SharedConfig.SETTINGS.getInt("data_source");

        if( source == DataSourceSettings.FLEET.ordinal() ){
            getCookieFromFleet();
        } else {
            getCookieFromServer();
        }
    }

    /**
     * Get cookie from fleet via web view
     */
    public static void getCookieFromFleet() {
        // @todo get fleet credentials

        if( !tryLoginWithoutCaptcha() ){
            // we will start captcha screen, embed it to popup
            CaptchaWebview captchaWebview = new CaptchaWebview();
            captchaWebview.initUI();
            captchaWebview.setListener(new ActionCallback() {
                @Override
                public void onSuccess(String... params) {
                    System.out.println("SUCCESS!");
                    System.out.println(CookieAgent.FILO5_COOKIE);
                    ReadyToCheckCookieFlag = true;
                    ThreadHelper.runOnUIThread( () -> {
                        // show loader again for bus fetch action
                        Popup.showMessage(Popup.DEFAULT, "Cookie kontrol ediliyor...");
                    });
                }
                @Override
                public void onError(int type) {
                    System.out.println("ERROR!!");
                    Platform.runLater(() -> {
                        captchaWebview.setNotf("Hatalı kod girildi.");
                    });
                }
            });

            Platform.runLater(() -> {
                Popup.setContent(captchaWebview.getUI());
            });
        }
    }

    /**
     * Get cookie from cookie server.
     */
    private static void getCookieFromServer() {
        // get cookie from api
        for( int k = 0; k < COOKIE_SERVER_URLS.length; k++ ){
            if( requestToCookieServer(COOKIE_SERVER_URLS[k]) ) break;
        }
    }

    /**
     * Make a request to cookie server with given url
     *
     * @param url
     * @return
     */
    private static boolean requestToCookieServer( String url ){
        Connection.Response res;
        try {
            res = Jsoup.connect(url)
                    .method(Connection.Method.POST)
                    .timeout(2000)
                    .execute();

            String newCookie = res.parse().text();
            try {
                if( !FILO5_COOKIE.equals( newCookie ) ){
                    FILO5_COOKIE = newCookie;
                }
            } catch( NullPointerException e ){
                FILO5_COOKIE = newCookie;
            }
            ReadyToCheckCookieFlag = true;
        } catch( IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if active cookie is valid or not.
     *
     * @return
     */
    public static boolean checkCookie(){
        if( !ReadyToCheckCookieFlag ){
            System.out.println("CookieAgent.Nothing to check");
            return false;
        }
        Connection.Response res;
        try {
            res = Jsoup.connect("https://filotakip.iett.gov.tr/_FYS/000/sorgu.php?konum=ana&konu=sefer&hat=15BK")
                    .method(Connection.Method.GET)
                    .cookie("PHPSESSID", FILO5_COOKIE)
                    .timeout(2000)
                    .execute();

            Document document = res.parse();
            try {
                document.getElementById("captcha").text();
                System.out.println("Invalid cookie!");

                // @todo we can login with invalid captcha at the moment, but when they
                // @todo fix it, we'll trigger CatpchaWebView from here. [22.11.2019]
                return false;
            } catch( NullPointerException e ){
                System.out.println("Valid cookie!");
                return true;
            }
        } catch( IOException e) {
            return false;
        }
    }

    /**
     * There is a bug in the fleet at the moment which allows us to login
     * with random 3 character captcha.
     *
     * @return
     */
    public static boolean tryLoginWithoutCaptcha(){
        org.jsoup.Connection.Response res;
        try{
            System.out.println( "Login without captcha");
            res = Jsoup.connect("https://filotakip.iett.gov.tr/login.php")
                    .method(org.jsoup.Connection.Method.POST)
                    .data("login", CookieAgent.FILO5_LOGIN, "password", CookieAgent.FILO5_PASS, "captcha", "xxx")
                    .timeout(0)
                    .execute();

            CookieAgent.FILO5_COOKIE = res.cookies().get("PHPSESSID");
            ReadyToCheckCookieFlag = true;
            return true;
        } catch( IOException e ){
            e.printStackTrace();
            return false;
        }
    }

}
