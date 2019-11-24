/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import cookie_agent.CookieAgent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.Route;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

@NoArgsConstructor
public class RouteDataDownloader extends IETTDataDownloader {

    @Getter
    private ArrayList<Route> data;

    /**
     * Download action
     */
    public void action(){
        errorFlag = false;

        getClearance();

        // clear list
        data = new ArrayList<>();

        String URL = "https://filotakip.iett.gov.tr/_FYS.php";

        try {
            org.jsoup.Connection.Response response = Jsoup.connect(URL)
                    .cookie("PHPSESSID", CookieAgent.FILO5_COOKIE )
                    .method(org.jsoup.Connection.Method.GET)
                    .timeout(50000)
                    .execute();

            release();

            parseData(response.parse());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse the data
     *
     * @param document
     */
    private void parseData(Document document) {

        try {
            Element routeSelect = document.getElementsByAttributeValue("name", "hatkodu").get(0);
            Elements options = routeSelect.getElementsByTag("option");

            for( int k = 1; k < options.size(); k++ ){
                Element option = options.get(k);
                System.out.println(option.attr("value") +"  --   " +  option.text());
            }

        } catch( Exception e ){
            e.printStackTrace();
        }


    }

}
