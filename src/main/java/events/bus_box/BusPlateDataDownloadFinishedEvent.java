package events.bus_box;

import interfaces.Postable;
import lombok.Getter;
import org.json.JSONObject;

public class BusPlateDataDownloadFinishedEvent implements Postable {

    @Getter
    private JSONObject newData;

    @Getter
    private String busCode;

    public BusPlateDataDownloadFinishedEvent(String busCode, JSONObject newData){
        this.newData = newData;
        this.busCode = busCode;
    }

}
