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

@AllArgsConstructor
@Data
public class BusMessage {

    /**
     * Message source
     */
    private String source;

    /**
     * Message
     */
    private String message;

    /**
     * Timestamp of the message
     */
    private String timestamp;

}
