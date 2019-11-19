/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import enums.AlarmType;
import ui.alarm.AlarmItem;

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

    /**
     * Getter for hidden flag
     *
     * @return
     */
    public boolean getSeen() {
        return seen;
    }

    /**
     * Setter for hidden flag
     *
     * @param seen
     */
    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    /**
     * Getter for ID
     *
     * @return
     */
    public String getID() {
        return ID;
    }

    /**
     * Setter for ID
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Getter for index
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter for index
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Getter for AlarmItem
     *
     * @return
     */
    public AlarmItem getUiComponent() {
        return uiComponent;
    }

    /**
     * Setter for UI component
     *
     * @param uiComponent
     */
    public void setUiComponent(AlarmItem uiComponent) {
        this.uiComponent = uiComponent;
    }
}
