/*
 *  Gitas Fleet Tracking System 2020
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import enums.BusRunStatus;
import models.BusRun;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Common;
import utils.SharedConfig;

public class FleetDataDownloaderServer extends FleetDataDownloader {

    /**
     * Constructor
     *
     * @param code
     */
    public FleetDataDownloaderServer(String code) {
        super(code);
        urlPrefix = Common.fillDownloadUrl(SharedConfig.SETTINGS.getString("plan_download_url_server"), code);
        fillDate(Common.getCurrentDate()); // @todo after 00:00 it fails, we should determine a threshold.
    }

    /**
     * Constructor with date
     *
     * @param code
     */
    public FleetDataDownloaderServer(String code, String date) {
        super(code);
        urlPrefix = Common.fillDownloadUrl(SharedConfig.SETTINGS.getString("plan_download_url_server"), code);
        fillDate(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parseData(String response) {
//        System.out.println(response);
        JSONArray data = new JSONObject(response).getJSONArray("data");// @todo versions

        int index = 0, activeIndex = -1, waitingIndex = -1;
        String statusCode = "";
        String status = "";
        BusRun tempRunData;
        JSONObject tempJsonData;
        for( int k = 0; k < data.length(); k++ ){
            tempJsonData = data.getJSONObject(k);

            String statusText = Common.regexTrim(tempJsonData.getString("status"));
            try {
                status = statusText.substring(0, 1);
                statusCode = statusText.substring(2);
            } catch( StringIndexOutOfBoundsException e ){ }

            // find the current or next run
            if( status.equals(BusRunStatus.A) && activeIndex == -1 ){ // first active sometimes there are two active runs ( duplicate )
                activeIndex = index;
            }
            if( status.equals(BusRunStatus.B) && waitingIndex == -1 ){ // first waiting
                waitingIndex = index;
            }
            index++;

            tempRunData = new BusRun(
                    code,
                    Common.regexTrim(tempJsonData.getString("bline")),
                    tempJsonData.getString("driver_id"), // driverCode
                    k+1, // departureNo
                    tempJsonData.getString("service"), // serviceCode
                    Common.regexTrim(tempJsonData.getString("location")),
                    Common.regexTrim(tempJsonData.getString("coming")), // arrival
                    Common.regexTrim(tempJsonData.getString("orer")), // ORER
                    Common.regexTrim(tempJsonData.getString("chief")), // alternativeORER
                    Common.regexTrim(tempJsonData.getString("going")), // departureTime
                    Common.regexTrim(String.valueOf(tempJsonData.get("guess"))), // estimatedEndTime
                    Common.regexTrim(tempJsonData.getString("finish")), // endTime
                    tempJsonData.getString("route"), // routeDetail
                    status,
                    statusCode
            );

            runData.add(tempRunData);
            try {
                runStatusSummary.put(status, runStatusSummary.get(status) + 1);
                runStatusSummary.put("TOTAL", runStatusSummary.get("TOTAL") + 1);
            } catch( NullPointerException e ){
                e.printStackTrace();
            }

            if( activeIndex > -1 ){
                activeRunIndex = activeIndex;
            } else if( waitingIndex > -1 ){
                activeRunIndex = waitingIndex;
            }
        }
    }

    /**
     * Fill %%DATE%% with given string
     *
     * @param date
     */
    private void fillDate(String date){
        urlPrefix = urlPrefix.replace("%%DATE%%", date);
    }
}
