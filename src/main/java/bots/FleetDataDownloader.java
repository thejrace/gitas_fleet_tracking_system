/*
 *  Gitas Fleet Tracking System 2020
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import enums.BusRunStatus;
import lombok.Getter;
import lombok.Setter;
import models.BusRun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FleetDataDownloader extends IETTDataDownloader {

    /**
     * Code of the bus
     */
    @Getter
    @Setter
    protected String code;

    /**
     * Route code of the bus
     */
    @Getter
    protected String routeCode;

    /**
     * Active run index counter
     */
    @Getter
    protected int activeRunIndex = -1;

    /**
     * All run data
     */
    @Getter
    protected ArrayList<BusRun> runData = new ArrayList<>();

    /**
     * Status summary
     */
    @Getter
    protected Map<String, Integer> runStatusSummary = new HashMap<>();

    @Getter
    protected String urlPrefix;

    /**
     * Constructor
     *
     * @param code
     */
    public FleetDataDownloader(String code){
        this.code = code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(){
        super.action();

        // clear list
        runData = new ArrayList<>();

        // reset summary counters
        runStatusSummary.put(BusRunStatus.A, 0);
        runStatusSummary.put(BusRunStatus.B, 0);
        runStatusSummary.put(BusRunStatus.T, 0);
        runStatusSummary.put(BusRunStatus.I, 0);
        runStatusSummary.put(BusRunStatus.Y, 0);
        runStatusSummary.put("TOTAL", 0);

        request(urlPrefix, org.jsoup.Connection.Method.GET, 50000 );
    }
}
