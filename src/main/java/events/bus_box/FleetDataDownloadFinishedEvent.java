package events.bus_box;

import bots.BusFleetDataDownloader;
import interfaces.Postable;
import lombok.Getter;
import models.Bus;
import repositories.BusStatusRepository;

public class FleetDataDownloadFinishedEvent implements Postable {

    @Getter
    private Bus bus;

    @Getter
    private BusStatusRepository busStatusRepository;

    @Getter
    private BusFleetDataDownloader busFleetDataDownloader;

    public FleetDataDownloadFinishedEvent(Bus bus, BusStatusRepository busStatusRepository, BusFleetDataDownloader busFleetDataDownloader ){
        this.bus = bus;
        this.busStatusRepository = busStatusRepository;
        this.busFleetDataDownloader = busFleetDataDownloader;
    }

}
