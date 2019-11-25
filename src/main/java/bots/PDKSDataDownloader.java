/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import enums.PDKSAction;
import lombok.Getter;
import lombok.Setter;
import models.PDKSRecord;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Common;

import java.util.ArrayList;

public class PDKSDataDownloader extends IETTDataDownloader {

    /**
     * Bus code of the bus
     */
    @Setter
    private String busCode;

    /**
     * Output data
     */
    @Getter
    private ArrayList<PDKSRecord> output;

    public PDKSDataDownloader(String busCode){
        this.busCode = busCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(){
        super.action();

        output = new ArrayList<>();

        String URL_PREFIX = "https://filotakip.iett.gov.tr/_FYS/000/sorgu.php?konu=mesaj&mtip=PDKS&oto="; // @todo get from settings

        request(URL_PREFIX + busCode, org.jsoup.Connection.Method.GET, 50000 );
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
                System.out.println(busCode + " PDKS Filo Veri Yok");
                return;
            }

            for (int i = 2; i < rows.size(); i++) {
                row = rows.get(i);
                cols = row.select("td");

                String actionLabel = cols.get(4).text();
                String action;
                String driverName;
                try {
                    if( actionLabel.contains(PDKSAction.IN) ) {
                        action = PDKSAction.IN;
                    } else {
                        action = PDKSAction.OUT;
                    }
                    driverName = Common.regexTrim(actionLabel.substring(actionLabel.indexOf("-")+2));
                    output.add(new PDKSRecord(
                            busCode,
                            Common.regexTrim(cols.get(2).text()), // source
                            Common.regexTrim(cols.get(3).text().trim()), // timestamp
                            action,
                            driverName
                    ));
                } catch( StringIndexOutOfBoundsException e ) {
                    e.printStackTrace();
                }
            }
        } catch( NullPointerException e ){
            e.printStackTrace();
            System.out.println( busCode + " PDKS veri ayıklama hatası.");
            errorFlag = true;
        }
    }

}
