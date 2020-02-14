/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import controllers.ControllerHub;
import cookie_agent.CookieAgent;
import enums.DataSourceSettings;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.APIRequest;
import utils.SharedConfig;

import java.io.IOException;

public class IETTDataDownloader {

    /**
     * Error flag
     */
    @Getter
    protected boolean errorFlag = false;

    /**
     * Error message
     */
    @Getter
    protected String errorMessage;

    /**
     * Download action. it's overridden in each child class
     */
    public void action(){
        errorFlag = false;
        errorMessage = "";

        ControllerHub.DownloaderController.getClearance();
    }

    /**
     * Requester method
     *
     * @param url
     * @param method
     * @param timeout
     *
     * @return
     */
    protected void request(String url, org.jsoup.Connection.Method method, int timeout){
        int source = SharedConfig.SETTINGS.getInt("data_source");
        if( source == DataSourceSettings.FLEET.ordinal() ){
            requestToFleet(url, method, timeout);
        } else {
            requestToAPI(url);
        }
    }

    public void requestToFleet(String url, org.jsoup.Connection.Method method, int timeout){
        try {
            org.jsoup.Connection.Response response = Jsoup.connect(url)
                    .cookie("PHPSESSID", CookieAgent.FILO5_COOKIE )
                    .method(method)
                    .timeout(timeout)
                    .execute();
            ControllerHub.DownloaderController.release();
            parseData(response.parse());
        } catch( IOException | NullPointerException e ){
            e.printStackTrace();
        }
    }

    public void requestToAPI(String url){
        try {
            String response = APIRequest.GET(url);
            ControllerHub.DownloaderController.release();
            parseData(response);
        } catch( NullPointerException e ){
            e.printStackTrace();
        }
    }

    /**
     * Data parser method, it's overridden in each child class (fleet)
     *
     * @param document
     */
    public void parseData( Document document ){

    }

    /**
     * Data parser method, it's overridden in each child class (server)
     *
     * @param data
     */
    public void parseData( String data ){

    }
}
