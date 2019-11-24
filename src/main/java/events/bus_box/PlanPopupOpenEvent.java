package events.bus_box;

import interfaces.Postable;
import lombok.Getter;
import models.Bus;

public class PlanPopupOpenEvent implements Postable {

    @Getter
    private Bus busData;

    public PlanPopupOpenEvent(Bus busData){
        this.busData = busData;



    }

}
