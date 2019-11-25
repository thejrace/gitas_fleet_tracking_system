/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import lombok.Setter;
import org.jsoup.nodes.Document;

public class DriverInfoDownloader extends IETTDataDownloader {

    /**
     * Code of the driver
     */
    @Setter
    private String code;

    public DriverInfoDownloader(String code){
        this.code = code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(){
        super.action();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void parseData(Document document){

    }

}
