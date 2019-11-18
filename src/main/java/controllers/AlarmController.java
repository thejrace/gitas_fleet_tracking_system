/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import models.Alarm;
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
        alarms.add(alarm);
    }

    /**
     * Mark as seen the alarm wtih given ID
     *
     * @param ID
     */
    public void markAsSeen( String ID ){
        alarmPopup.removeAlarm(ID);
    }

    /**
     * Mark as seen all visible alarms
     */
    public void markAllAsSeen(){

    }

    /**
     * Unmark all seen alarms
     */
    public void markAllAsNotSeen(){

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
