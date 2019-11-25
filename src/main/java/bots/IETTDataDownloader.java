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
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.ThreadHelper;

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

        getClearance();
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
        try {
            org.jsoup.Connection.Response response = Jsoup.connect(url)
                    .cookie("PHPSESSID", CookieAgent.FILO5_COOKIE )
                    .method(method)
                    .timeout(timeout)
                    .execute();

            release();

            parseData(response.parse());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Data parser method, it's overridden in each child class
     *
     * @param document
     */
    protected void parseData( Document document ){

    }

    /**
     * Give back the clearance
     */
    protected void release(){
        ControllerHub.DownloaderController.release();
    }

    /**
     *  Wait until we get clearance from fleet request limiter
     */
    protected void getClearance(){
        while(!ControllerHub.DownloaderController.request()){
            ThreadHelper.delay(10);
        }
    }
}
