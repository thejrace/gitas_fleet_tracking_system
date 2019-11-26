package bots;

import lombok.Getter;
import lombok.Setter;
import models.BusMessage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Common;

import java.util.ArrayList;

public class BusMessagesDownloader extends IETTDataDownloader {

    /**
     * Bus code of the bus
     */
    @Setter
    private String busCode;

    /**
     * Output data
     */
    @Getter
    private ArrayList<BusMessage> data;

    public BusMessagesDownloader(String busCode){
        this.busCode = busCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(){
        super.action();

        data = new ArrayList<>();

        String[] URL_LIST = {
                "https://filotakip.iett.gov.tr/_FYS/000/sorgu.php?konu=mesaj&mtip=giden&oto=", // @todo get from settings
                "https://filotakip.iett.gov.tr/_FYS/000/sorgu.php?konu=mesaj&mtip=gelen&oto="
        };

        for( int k = 0; k < URL_LIST.length; k++){
            request(URL_LIST[k] + busCode, org.jsoup.Connection.Method.GET, 50000 );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void parseData(Document document){

        Elements table;
        Elements rows;
        Element row;
        Elements cols;

        try {
            table = document.select("table");
            rows = table.select("tr");

            if( rows.size() == 1 || rows.size() == 0 ){
                System.out.println(busCode + " Mesaj Filo Veri Yok");
                return;
            }

            for (int i = 2; i < rows.size(); i++) {
                row = rows.get(i);
                cols = row.select("td");

                /*System.out.println("["+Common.regexTrim(cols.get(2).text()));
                System.out.println("["+Common.regexTrim(cols.get(3).text()));
                System.out.println("["+Common.regexTrim(cols.get(4).text()));*/

                data.add(new BusMessage(
                        Common.regexTrim(cols.get(2).text()),
                        Common.regexTrim(cols.get(4).text()),
                        Common.regexTrim(cols.get(3).text())
                ));

            }
        } catch( NullPointerException e ){
            e.printStackTrace();
            System.out.println( busCode + " Mesaj veri ayıklama hatası.");
            errorFlag = true;
        }
    }

}
