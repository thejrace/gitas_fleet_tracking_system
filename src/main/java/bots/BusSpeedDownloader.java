/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import lombok.Getter;
import org.jsoup.nodes.Document;


public class BusSpeedDownloader extends IETTDataDownloader {

    /**
     * Code of the bus
     */
    private String busCode;

    /**
     * Downloaded speed
     */
    @Getter
    private int speed;

    /**
     * Constructor
     *
     * @param busCode
     */
    public BusSpeedDownloader(String busCode){
        this.busCode = busCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(){
        super.action();

        String URL_PREFIX = "https://filotakip.iett.gov.tr/_FYS/000/harita.php?konu=oto&oto="; // @todo get from settings

        request(URL_PREFIX + busCode, org.jsoup.Connection.Method.GET, 50000);
    }

    /**
     * {@inheritDoc}
     */
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
