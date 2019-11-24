package events.bus_box;

import interfaces.Postable;
import lombok.Getter;
import models.Bus;

public class FleetDataDownloadEvent implements Postable {

    @Getter
    private Bus busData;

    /**
     * Trigger BusController to download fleet data for the given bus
     */
    public FleetDataDownloadEvent(Bus busData){
        this.busData = busData;
    }

}
