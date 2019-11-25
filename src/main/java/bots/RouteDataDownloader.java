/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import lombok.Getter;
import lombok.NoArgsConstructor;
import models.Route;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

@NoArgsConstructor
public class RouteDataDownloader extends IETTDataDownloader {

    @Getter
    private ArrayList<Route> data;

    /**
     * Download action
     */
    public void action(){
        super.action();

        // clear list
        data = new ArrayList<>();

        String URL = "https://filotakip.iett.gov.tr/_FYS.php";

        request(URL, org.jsoup.Connection.Method.GET, 50000);
    }

    /**
     * Parse the data
     *
     * @param document
     */
    @Override
    protected void parseData(Document document) {
        try {
            Element routeSelect = document.getElementsByAttributeValue("name", "hatkodu").get(0);
            Elements options = routeSelect.getElementsByTag("option");

            for( int k = 1; k < options.size(); k++ ){
                Element option = options.get(k);

                String fullName = option.text();
                int fullNameCropIndex = fullName.indexOf(" ");
                String code = fullName.substring(0, fullNameCropIndex);
                String name = fullName.substring(fullNameCropIndex+1);

                data.add(new Route(
                        code,
                        name,
                        option.attr("value")
                ));
            }
        } catch( Exception e ){
            e.printStackTrace();
        }
    }
}
