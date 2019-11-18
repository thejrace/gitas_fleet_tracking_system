/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package model;

import enums.AlarmType;

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
    }

    /**
     * Getter for title
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for details
     *
     * @return
     */
    public String getDetails() {
        return details;
    }

    /**
     * Setter for details
     *
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Getter for type
     *
     * @return
     */
    public AlarmType getType() {
        return type;
    }

    /**
     * Setter for type
     *
     * @param type
     */
    public void setType(AlarmType type) {
        this.type = type;
    }
}
