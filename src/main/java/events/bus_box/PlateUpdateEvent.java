/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package events.bus_box;

import interfaces.Postable;
import lombok.Getter;
import models.Bus;

public class PlateUpdateEvent implements Postable {

    @Getter
    private Bus busData;

    /**
     * Event to trigger BusBox to update plate UI element
     *
     * @param busData
     */
    public PlateUpdateEvent(Bus busData) {
        this.busData = busData;
    }

}
