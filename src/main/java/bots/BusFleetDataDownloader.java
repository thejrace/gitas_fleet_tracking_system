/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import enums.BusRunStatus;
import lombok.Getter;
import models.BusRun;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Common;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BusFleetDataDownloader extends IETTDataDownloader {

    /**
     * Code of the bus
     */
    @Getter
    private String code;

    /**
     * Route code of the bus
     */
    @Getter
    private String routeCode;

    /**
     * Active run index counter
     */
    @Getter
    private int activeRunIndex = -1;

    /**
     * All run data
     */
    @Getter
    private ArrayList<BusRun> runData = new ArrayList<>();

    /**
     * Status summary
     */
    @Getter
    private Map<String, Integer> runStatusSummary = new HashMap<>();

    /**
     * Constructor
     *
     * @param code
     */
    public BusFleetDataDownloader(String code){
        this.code = code;
    }

    /**
     * Download action
     */
    @Override
    public void action(){
        super.action();

        // clear list
        runData = new ArrayList<>();

        // reset summary counters
        runStatusSummary.put(BusRunStatus.A, 0);
        runStatusSummary.put(BusRunStatus.B, 0);
        runStatusSummary.put(BusRunStatus.T, 0);
        runStatusSummary.put(BusRunStatus.I, 0);
        runStatusSummary.put(BusRunStatus.Y, 0);
        runStatusSummary.put("TOTAL", 0);

        String URL_PREFIX = "https://filotakip.iett.gov.tr/_FYS/000/sorgu.php?konum=ana&konu=sefer&otobus="; // @todo get from settings

        request(URL_PREFIX + code, org.jsoup.Connection.Method.GET, 50000 );
    }

    /**
     * Parse the document of the fleet
     *
     * @param document
     */
    @Override
    protected void parseData( Document document ){

        Elements table;
        Elements rows;
        Element row;
        Elements cols;

        try {
            table = document.select("table");
            rows = table.select("tr");

            if( rows.size() == 1 || rows.size() == 0 ){
                System.out.println(code + " ORER Filo Veri Yok");
                return;
            }

            String routeCode = "";
            BusRun tempRunData;
            boolean routeCodeFetchedFlag = false;
            String activeStop = "N/A";

            int index = 0, activeIndex = -1, waitingIndex = -1;


            for( int i = 1; i < rows.size(); i++ ) {
                row = rows.get(i);
                cols = row.select("td");

                if( !routeCodeFetchedFlag ){
                    // route code sometimes have characters at the begining and the end, clear them
                    routeCode = cols.get(2).text().trim();
                    if( cols.get(2).text().trim().contains("!")  ){
                        routeCode = cols.get(2).text().trim().substring(3, cols.get(2).text().trim().length() - 1 );
                    } else if( cols.get(2).text().trim().contains("#") ) {
                        routeCode = cols.get(2).text().trim().substring(3, cols.get(2).text().trim().length() - 1 );
                    } else  if( cols.get(2).text().trim().contains("*") ){
                        routeCode = cols.get(2).text().trim().substring(3, cols.get(2).text().trim().length() - 1);
                    } else {
                        routeCode = routeCode.substring(2, routeCode.length());
                    }
                    routeCodeFetchedFlag = true;
                    this.routeCode = routeCode;
                }


                String statusLabel = "";
                String status = "";
                String statusCode = "";
                try {
                    statusLabel = cols.get(14).text();
                    status = statusLabel.substring(0,1);
                    statusCode = statusLabel.substring(2, statusLabel.length());

                } catch (StringIndexOutOfBoundsException e ){
                    //e.printStackTrace();
                }

                // find the current or next run
                if( status.equals(BusRunStatus.A) && activeIndex == -1 ){ // first active sometimes there are two active runs ( duplicate )
                    activeIndex = index;
                }
                if( status.equals(BusRunStatus.B) && waitingIndex == -1 ){ // first waiting
                    waitingIndex = index;
                }
                index++;

                // get available stop data
                String activeStopLabel = Common.regexTrim(cols.get(15).text());
                if( activeStop.equals("N/A") && !activeStopLabel.equals("")){
                    activeStop = activeStopLabel;
                }

                tempRunData = new BusRun(
                        code,
                        routeCode,
                        Common.regexTrim(cols.get(7).text()), // driverCode
                        Integer.valueOf(Common.regexTrim(cols.get(0).text())), // departureNo
                        Common.regexTrim(cols.get(3).text()), // serviceCode
                        activeStop,
                        Common.regexTrim(cols.get(8).text()), // arrival
                        Common.regexTrim(cols.get(9).getAllElements().get(2).text()), // ORER
                        Common.regexTrim(cols.get(10).text()), // alternativeORER
                        Common.regexTrim(cols.get(11).text()), // departureTime
                        Common.regexTrim(cols.get(12).text()), // estimatedEndTime
                        Common.regexTrim(cols.get(13).text()), // endTime
                        Common.regexTrim(cols.get(4).getAllElements().get(1).text()), // routeDetail
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
            }


            if( activeIndex > -1 ){
                activeRunIndex = activeIndex;
            } else if( waitingIndex > -1 ){
                activeRunIndex = waitingIndex;
            }

            rows.clear();
        } catch( NullPointerException e ){
            e.printStackTrace();
            System.out.println( code + " ORER sefer veri ayıklama hatası. Tekrar deneniyor.");
            errorFlag = true;
        }
    }
}
