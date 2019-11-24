/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import enums.AlarmType;
import lombok.Data;
import ui.alarm.AlarmItem;

@Data
public class Alarm {

    /**
     * Title string
     */
    private String title;

    /**
     * Detail string
     */
    private String details;

    /**
     * Type of the alarm
     */
    private AlarmType type;

    /**
     * ID of the Alarm. Used to access AlarmItem UI component
     */
    private String ID;

    /**
     * Index of the Alarm in the list
     */
    private int index;

    /**
     * Status of the alarm. It can be seen or disabled in the settings.
     */
    private boolean seen = false;

    /**
     * UI component of the alarm
     */
    private AlarmItem uiComponent;

    /**
     * Constructor
     *
     * @param type
     * @param title
     * @param details
     */
    public Alarm( AlarmType type, String title, String details){
        this.type = type;
        this.title = title;
        this.details = details;
        this.ID = type.name()+"-"+title;
    }
}
