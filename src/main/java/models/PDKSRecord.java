/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PDKSRecord {

    /**
     * Code of the bus
     */
    private String busCode;

    /**
     * Source of the action
     */
    private String source;

    /**
     * Timestamp of the action
     */
    private String timestamp;

    /**
     * Action type ( PDKSAction )
     */
    private String action;

    /**
     * Name of the driver
     */
    private String driverName;

}
