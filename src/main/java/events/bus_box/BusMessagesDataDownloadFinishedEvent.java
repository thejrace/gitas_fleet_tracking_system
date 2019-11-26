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
import models.BusMessage;

import java.util.ArrayList;

@AllArgsConstructor
public class BusMessagesDataDownloadFinishedEvent implements Postable {

    @Getter
    private Bus busData;

    @Getter
    private ArrayList<BusMessage> data;

}
