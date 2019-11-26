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
import org.json.JSONObject;

@AllArgsConstructor
public class BusPlateDataDownloadFinishedEvent implements Postable {

    @Getter
    private String busCode;

    @Getter
    private JSONObject newData;

}
