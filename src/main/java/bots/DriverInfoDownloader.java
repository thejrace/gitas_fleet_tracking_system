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
import models.BusDriver;
import models.PDKSRecord;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.ThreadHelper;

import java.util.ArrayList;

@NoArgsConstructor
public class DriverInfoDownloader extends IETTDataDownloader {

    /**
     * PDKS data of the bus
     */
    @Setter
    private ArrayList<PDKSRecord> pdksRecords;

    /**
     * Output data
     */
    @Getter
    private ArrayList<BusDriver> data;

    private int runnerThreadCounter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(){
        super.action();

        data = new ArrayList<>();
        runnerThreadCounter = 0;

        try {
            for( PDKSRecord pdksRecord : pdksRecords ){
                // start all scans parallel
                ThreadHelper.func(() -> {
                    getClearance();
                    request("https://filotakip.iett.gov.tr/fysDosya/sofor/"+pdksRecord.getSource()+".htm", org.jsoup.Connection.Method.GET, 50000 );
                    runnerThreadCounter++;
                });
            }
        } catch( NullPointerException e ){
            errorFlag = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void parseData(Document document){

        Elements table = null;
        Elements rows  = null;

        Element row    = null;
        Elements cols  = null;

        try {
            table = document.select("table");
            rows = table.select("tr");

            String imgSrc = document.select("img").get(0).attr("src");
            String code = rows.get(1).select("td").get(1).text();
            String name = "N/A";

            /*System.out.println("["+code+"]");
            System.out.println("["+rows.get(3).select("td").get(1).text()+"]");
            System.out.println("["+rows.get(9).select("td").get(1).text()+"]");
            System.out.println("["+imgSrc+"]");*/

            for( PDKSRecord pdksRecord : pdksRecords ){ // @todo FIX this!!
                if( code.equals(pdksRecord.getSource())){
                    name = pdksRecord.getDriverName();
                    break;
                }
            }

            data.add(new BusDriver(
                    rows.get(1).select("td").get(1).text(), // code
                    name,
                    rows.get(3).select("td").get(1).text(), // phone
                    rows.get(9).select("td").get(1).text(), // tc
                    imgSrc // img src
            ));

        } catch( Exception e ){
            e.printStackTrace();
        }

    }

    /**
     * Determine if all runner threads are finished downloading.
     *
     * @return
     */
    public boolean ready() {
        return runnerThreadCounter == pdksRecords.size();
    }
}
