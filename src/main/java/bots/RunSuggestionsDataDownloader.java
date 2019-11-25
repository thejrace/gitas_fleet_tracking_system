/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import enums.BusRunStatus;
import events.RunSuggestionsStatusUpdateEvent;
import interfaces.Subscriber;
import lombok.Getter;
import lombok.Setter;
import models.BusRun;
import models.Route;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Common;
import utils.GitasEventBus;
import utils.RunTimeDiff;
import utils.ThreadHelper;

import java.util.ArrayList;

public class RunSuggestionsDataDownloader extends IETTDataDownloader implements Subscriber {

    @Getter
    private ArrayList<BusRun> data;

    @Setter
    private ArrayList<Route> routes;

    @Getter
    private int runnerThreadCounter = 0;

    public RunSuggestionsDataDownloader(){
        GitasEventBus.register(this);
    }

    /**
     * Download action
     */
    @Override
    public void action(){
        super.action();

        runnerThreadCounter = 0;

        // clear list
        data = new ArrayList<>();

        String URL_PREFIX = "https://filotakip.iett.gov.tr/_FYS/000/sorgu.php?konum=ana&konu=sefer&hat=";

        GitasEventBus.post(new RunSuggestionsStatusUpdateEvent("Hatlar taranıyor.."));

        int size = routes.size();

        for( int k = 0; k < routes.size(); k++ ){
            Route route = routes.get(k);

            // start all scans parallel
            ThreadHelper.func(() -> {
                getClearance();
                System.out.println(route.getCode() +" run suggestion scan!");
                request(URL_PREFIX + route.getUrlSafeCode(), org.jsoup.Connection.Method.GET, 50000);
                System.out.println(route.getCode() +" run suggestion scan FINISHED!");
                runnerThreadCounter++;
                GitasEventBus.post(new RunSuggestionsStatusUpdateEvent("Hatlar taranıyor.( " + runnerThreadCounter + "/"+size + " tamamlandı. )" ));
            });
        }
    }

    @Override
    protected void parseData(Document document){
        Elements table = null;
        Elements rows  = null;
        Element row    = null;
        Elements cols  = null;

        try {
            table = document.select("table");
            rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) {

                row = rows.get(i);
                cols = row.select("td");

                String ORER = Common.regexTrim(cols.get(9).getAllElements().get(2).text());
                String statusLabel = "";
                String status = "";
                String statusCode = "";

                try {
                    statusLabel = cols.get(14).text();
                    status = statusLabel.substring(0,1);
                    statusCode = statusLabel.substring(2);

                } catch (StringIndexOutOfBoundsException e ){
                    //e.printStackTrace();
                }

                // list a cancelled run if its ORER is in the future.
                if( ( status.equals(BusRunStatus.I) || status.equals(BusRunStatus.Y) ) && !RunTimeDiff.isPast( Common.getCurrentHMin(), ORER )  ){
                    data.add(new BusRun(
                            Common.regexTrim(cols.get(5).text()), // code
                            Common.regexTrim(cols.get(4).getAllElements().get(1).text()),
                            ORER,
                            status,
                            statusCode
                    ));
                }
            }
        } catch( Exception e ){
            e.printStackTrace();
        }
    }

    /**
     * Determine if all runner threads are finished downloading.
     *
     * @return
     */
    public boolean ready() {
        return runnerThreadCounter == routes.size();
    }
}
