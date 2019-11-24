package bots;

import cookie_agent.CookieAgent;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class BusSpeedDownloader extends IETTDataDownloader {

    private String busCode;

    @Getter
    private int speed;

    public BusSpeedDownloader(String busCode){
        this.busCode = busCode;
    }

    public void action(){
        errorFlag = false;

        getClearance();

        String URL_PREFIX = "https://filotakip.iett.gov.tr/_FYS/000/harita.php?konu=oto&oto="; // @todo get from settings

        try {
            org.jsoup.Connection.Response response = Jsoup.connect(URL_PREFIX + busCode)
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

    private void parseData( Document document ){
        try {
            String page = document.toString();
            String dataString = page.substring( page.indexOf("veri_ilklendir")+14, page.indexOf("veri_hatcizgi") );
            String[] exploded = dataString.split("\\|");
            try {
                speed = Integer.valueOf(exploded[4]);
            } catch( ArrayIndexOutOfBoundsException e ){
                speed = 0;
            }
        } catch( NullPointerException e ){
            e.printStackTrace();
            errorFlag = true;
            errorMessage = "Veri alım hatası!";
            System.out.println(busCode + " -- hiz veri alim hatası");
        }
    }
}
