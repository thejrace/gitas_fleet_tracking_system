/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import controllers.ControllerHub;
import lombok.Getter;
import utils.ThreadHelper;

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
     *  Wait until we get clearance from fleet request limiter
     */
    protected void getClearance(){
        //
        while(!ControllerHub.DownloaderController.request()){
            ThreadHelper.delay(100);
        }
    }

    /**
     * Give back the clearance
     */
    protected void release(){
        ControllerHub.DownloaderController.release();
    }

}
