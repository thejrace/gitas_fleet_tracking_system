package bots;

import cookie_agent.CookieAgent;
import models.BusRun;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BusFleetDataDownloader {

    private static String URL_PREFIX = "https://filotakip.iett.gov.tr/_FYS/000/sorgu.php?konum=ana&konu=sefer&otobus=";

    private String code;

    private boolean errorFlag = false;

    private String routeCode;

    private int activeRunIndex = -1;

    private ArrayList<BusRun> runData = new ArrayList<>();

    private Map<String, Integer> runStatusSummary = new HashMap<>();

    public BusFleetDataDownloader(String code){
        this.code = code;
    }

    public void action(){
        errorFlag = false;

        // reset summary counters
        runStatusSummary.put("A", 0);
        runStatusSummary.put("B", 0);
        runStatusSummary.put("T", 0);
        runStatusSummary.put("I", 0);
        runStatusSummary.put("Y", 0);
        runStatusSummary.put("TOTAL", 0);

        try {
            org.jsoup.Connection.Response response = Jsoup.connect(URL_PREFIX + code)
                    .cookie("PHPSESSID", CookieAgent.FILO5_COOKIE )
                    .method(org.jsoup.Connection.Method.POST)
                    .timeout(50000)
                    .execute();

            parseFleetData(response.parse());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void parseFleetData( Document document ){

        Elements table = null;
        Elements rows = null;
        Element row = null;
        Elements cols = null;

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

                    runStatusSummary.put(status, runStatusSummary.get(status) + 1);

                } catch (StringIndexOutOfBoundsException e ){
                    //e.printStackTrace();
                }

                // find the current or next run
                if( status.equals("A") && activeIndex == -1 ){ // first active sometimes there are two active runs ( duplicate )
                    activeIndex = index;
                }
                if( status.equals("B") && waitingIndex == -1 ){ // first waiting
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
                runStatusSummary.put("TOTAL", runStatusSummary.get("TOTAL") + 1);
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

    public ArrayList<BusRun> getRunData() {
        return runData;
    }

    public Map<String, Integer> getRunStatusSummary(){
        return runStatusSummary;
    }

    public int getActiveRunIndex(){
        return activeRunIndex;
    }

    public String getRouteCode(){
        return routeCode;
    }

    public boolean getErrorFlag(){
        return errorFlag;
    }

}
