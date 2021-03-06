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
     * List of alarm ID's to prevent adding same alarm
     */
    private ArrayList<String> alarmIdList;

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
        alarmIdList = new ArrayList<>();
    }

    /**
     * Read user settings and then start Alarm system
     */
    public void start(){
        // @todo read user settings
        alarmPopup.start();
    }

    /**
     * Adds new alarm
     *
     * @param alarm
     */
    public void addAlarm( Alarm alarm ){
        if( alarmIdList.contains(alarm.getID()) ) return;
        alarm.setIndex(indexCounter);
        alarms.add(alarm);
        alarmIdList.add(alarm.getID());
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
            if( alarm.isSeen() ) continue;
            alarm.setSeen(true);
            alarmPopup.removeAlarm(alarm.getID());
        }
    }

    /**
     * Unmark all seen alarms
     */
    public void markAllAsNotSeen(){
        for( Alarm alarm : alarms ){
            if( !alarm.isSeen() ) continue;
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
