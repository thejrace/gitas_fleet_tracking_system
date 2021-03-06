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
import lombok.Setter;
import models.Route;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.APIRequest;
import utils.Common;

import java.util.ArrayList;

@NoArgsConstructor
public class RouteDataDownloader extends IETTDataDownloader {

    /**
     * Output data
     */
    @Getter
    private ArrayList<Route> data;

    /**
     * Flag to decide which data source will be used
     */
    @Setter
    private boolean apiFlag = true;

    /**
     * {@inheritDoc}
     */
    public void action(){
        // clear list
        data = new ArrayList<>();

        if( apiFlag ){
            try{
                JSONArray response = new JSONObject(APIRequest.GET(APIRequest.API_URL+"routes/filo5")).getJSONArray("routes");
                for( int k = 0; k < response.length(); k++ ){
                    String code = response.getString(k);
                    data.add(new Route(
                            code,
                            null,
                            Common.convertToSafeLink(code)
                    ));
                }
            } catch( JSONException e ){
                e.printStackTrace();
            }

        } else {
            super.action();

            String URL = "https://filotakip.iett.gov.tr/_FYS.php";

            request(URL, org.jsoup.Connection.Method.GET, 50000);
        }
    }

    /**
     * {@inheritDoc}
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
