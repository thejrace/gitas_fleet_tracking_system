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
import models.PDKSRecord;

import java.util.ArrayList;

@AllArgsConstructor
public class PDKSDataDownloadFinishedEvent implements Postable {

    private ArrayList<PDKSRecord> data;

}
