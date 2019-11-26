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

@AllArgsConstructor
public class BusSpeedDownloadFinishedEvent implements Postable {

    @Getter
    private String busCode;

    @Getter
    private int speed;

}
