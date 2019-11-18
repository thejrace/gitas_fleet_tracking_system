package bots;

import cookie_agent.CookieAgent;
import models.BusRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class BusDataDownloader {

    private static String URL_PREFIX = "https://filotakip.iett.gov.tr/_FYS/000/sorgu.php?konum=ana&konu=sefer&otobus=";

    private String code;

    private ArrayList<BusRun> runData = new ArrayList<>();

    public BusDataDownloader(String code){
        this.code = code;
    }

    private void action(){
        try {
            org.jsoup.Connection.Response response = Jsoup.connect(URL_PREFIX + code)
                    .cookie("PHPSESSID", CookieAgent.FILO5_COOKIE )
                    .method(org.jsoup.Connection.Method.POST)
                    .timeout(50000)
                    .execute();

            parseData(response.parse());

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void parseData( Document document ){



    }

    public ArrayList<BusRun> getRunData() {
        return runData;
    }

}
