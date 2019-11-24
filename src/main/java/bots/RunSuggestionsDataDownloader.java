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
import models.BusRun;
import models.Route;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

@NoArgsConstructor
public class RunSuggestionsDataDownloader extends IETTDataDownloader {

    @Getter
    private ArrayList<BusRun> data;

    @Setter
    private ArrayList<Route> routes;

    public void action(){

    }

    public void processData(Document document){

    }

}
