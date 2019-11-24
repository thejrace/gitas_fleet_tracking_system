package events.bus_box;

import interfaces.Postable;
import lombok.Getter;

public class BusSpeedDownloadFinishedEvent implements Postable {

    @Getter
    private int speed;

    @Getter
    private String busCode;

    public BusSpeedDownloadFinishedEvent(String busCode, int speed ){
        this.speed = speed;
        this.busCode = busCode;
    }

}
