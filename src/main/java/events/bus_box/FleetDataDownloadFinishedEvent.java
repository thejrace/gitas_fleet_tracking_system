/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package events.bus_box;

import bots.BusFleetDataDownloader;
import interfaces.Postable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import models.Bus;
import repositories.BusStatusRepository;

@AllArgsConstructor
public class FleetDataDownloadFinishedEvent implements Postable {

    @Getter
    private Bus bus;

    @Getter
    private BusStatusRepository busStatusRepository;

    @Getter
    private BusFleetDataDownloader busFleetDataDownloader;

}
