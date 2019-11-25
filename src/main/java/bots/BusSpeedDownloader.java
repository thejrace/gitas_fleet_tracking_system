package bots;

import lombok.Getter;
import org.jsoup.nodes.Document;


public class BusSpeedDownloader extends IETTDataDownloader {

    private String busCode;

    @Getter
    private int speed;

    public BusSpeedDownloader(String busCode){
        this.busCode = busCode;
    }

    /**
     * Download action
     */
    @Override
    public void action(){
        super.action();

        String URL_PREFIX = "https://filotakip.iett.gov.tr/_FYS/000/harita.php?konu=oto&oto="; // @todo get from settings

        request(URL_PREFIX + busCode, org.jsoup.Connection.Method.GET, 50000);
    }

    @Override
    protected void parseData( Document document ){
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
