/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import models.Alarm;
import ui.alarm.AlarmItem;
import ui.alarm.AlarmPopup;

import java.util.ArrayList;

public class AlarmController {

    /**
     * List of alarms
     */
    private ArrayList<Alarm> alarms;

    /**
     * Popup UI component
     */
    private AlarmPopup alarmPopup;

    /**
     * Counter for the alarms array
     */
    private int indexCounter = 0;

    /**
     * Empty constructor
     */
    public AlarmController(){

    }

    /**
     * Initializes data and UI component
     */
    public void initialize(){
        alarmPopup = new AlarmPopup();
        alarmPopup.initialize();
        alarms = new ArrayList<>();
    }

    /**
     * Adds new alarm
     *
     * @param alarm
     */
    public void addAlarm( Alarm alarm ){
        alarm.setIndex(indexCounter);
        alarms.add(alarm);
        indexCounter++;
        alarm.setUiComponent(new AlarmItem(alarm));
        alarmPopup.addAlarm(alarm);
    }

    /**
     * Mark as seen the alarm
     *
     * @param alarm
     */
    public void markAsSeen( Alarm alarm ){
        alarmPopup.removeAlarm(alarm.getID());
        alarms.get(alarm.getIndex()).setSeen(true);
    }

    /**
     * Mark as seen all visible alarms
     */
    public void markAllAsSeen(){
        for( Alarm alarm : alarms ){
            if( alarm.getSeen() ) continue;
            alarm.setSeen(true);
            alarmPopup.removeAlarm(alarm.getID());
        }
    }

    /**
     * Unmark all seen alarms
     */
    public void markAllAsNotSeen(){
        for( Alarm alarm : alarms ){
            if( !alarm.getSeen() ) continue;
            alarm.setSeen(false);
            alarmPopup.addAlarm(alarm);
        }
        showAlarms();
    }

    /**
     * Show alarm popup
     */
    public void showAlarms(){
        alarmPopup.show();
    }

    /**
     * Hide alarm popup
     */
    public void hideAlarms(){
        alarmPopup.hide();
    }

    /**
     * Update trigger action state of the popup
     *
     * @param newState
     */
    public void updateTriggerFlag( boolean newState ){
        alarmPopup.setTriggerAction(newState);
    }

    /**
     * Update mouse overlay state of the popup
     *
     * @param newState
     */
    public void updateMouseOverlayFlag( boolean newState ){
        alarmPopup.setMouseOverFlag(newState);
    }

}
