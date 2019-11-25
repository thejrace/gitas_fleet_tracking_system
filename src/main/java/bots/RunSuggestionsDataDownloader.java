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
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.BusRun;
import models.Route;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Common;
import utils.RunTimeDiff;
import utils.ThreadHelper;

import java.util.ArrayList;

@NoArgsConstructor
public class RunSuggestionsDataDownloader extends IETTDataDownloader {

    @Getter
    private ArrayList<BusRun> data;

    @Setter
    private ArrayList<Route> routes;

    /**
     * Download action
     */
    @Override
    public void action(){
        super.action();

        // clear list
        data = new ArrayList<>();

        String URL_PREFIX = "https://filotakip.iett.gov.tr/_FYS/000/sorgu.php?konum=ana&konu=sefer&hat=";

        for( int k = 0; k < routes.size(); k++ ){
            Route route = routes.get(k);

            // start all scans parallel
            ThreadHelper.func(() -> {
                getClearance();
                System.out.println(route.getCode() +" run suggestion scan!");
                request(URL_PREFIX + route.getUrlSafeCode(), org.jsoup.Connection.Method.GET, 50000);
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
}
