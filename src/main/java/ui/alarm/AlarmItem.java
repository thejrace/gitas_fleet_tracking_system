/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.alarm;

import models.Alarm;
import ui.UIComponent;

public class AlarmItem extends UIComponent {

    /**
     * Model
     */
    private Alarm alarm;

    /**
     * Height of each item. Used to resize AlarmPopup
     */
    public static double HEIGHT = 40;

    /**
     * Empty constructor
     */
    public AlarmItem(){

    }

    /**
     * Initialize fxml
     */
    public void initUI(){
        loadFXML("alarm_item");
    }

    /**
     * Setter for alarm data
     *
     * @param alarm
     */
    public void setData(Alarm alarm){
        this.alarm = alarm;
        ((AlarmItemController)getController()).setData(alarm);
    }

}
