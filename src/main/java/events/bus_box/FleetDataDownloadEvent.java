/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package events.bus_box;

import interfaces.Postable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import models.Bus;

@AllArgsConstructor
public class FleetDataDownloadEvent implements Postable {

    @Getter
    private Bus busData;

}
