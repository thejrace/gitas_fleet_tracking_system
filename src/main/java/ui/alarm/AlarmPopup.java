/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.alarm;

import javafx.scene.layout.FlowPane;
import javafx.stage.Popup;
import models.Alarm;
import ui.MainScreenController;
import ui.UIComponent;
import utils.ThreadHelper;

public class AlarmPopup extends UIComponent {

    /**
     * Base Popup component
     */
    public Popup popup;

    /**
     * Alarm holder container
     */
    private FlowPane container;

    /**
     * Visibility state of the popup
     */
    private boolean shown = false;

    /**
     * Flag to determine if mouse is over the alarm popup
     */
    private boolean mouseOver = false;

    /**
     * Dynamic height of the container
     */
    private double containerHeight = 0;

    /**
     * Trigger flag
     */
    private boolean triggerEnabledFlag = true;

    /**
     * Empty constructor
     */
    public AlarmPopup(){

    }

    /**
     * Initialize the AlarmPopup
     */
    public void initialize(){
        // create the Popup instance
        popup = new Popup();

        // load fxml
        loadFXML("alarm_popup");

        // inject alarm holder container to popup
        container = (FlowPane)getUI();
        popup.getContent().add(container);

        // set settings of the Popup
        popup.setAutoHide(false);
        popup.setAutoHide(false);
        popup.setHideOnEscape(true);
    }

    /**
     * Start showing alarms
     */
    public void start(){
        triggerAction();
    }

    /**
     * Shows the popup
     */
    public void show(){
        popup.show(MainScreenController.WRAPPER.getScene().getWindow(), 10, 10);
        shown = true;
    }

    /**
     * Hides the popup
     */
    public void hide(){
        if( !mouseOver ){
            popup.hide();
            shown = false;
        }
    }

    /**
     * Show and hide popup at each interval
     */
    public void triggerAction(){
        ThreadHelper.func(() -> {
            while( triggerEnabledFlag ){
                if( !shown ) ThreadHelper.runOnUIThread(() -> show() );

                ThreadHelper.delay(10000);

                ThreadHelper.runOnUIThread(() -> hide() );

                ThreadHelper.delay(10000);
            }
        });

    }

    /**
     * Change the state of the trigger action
     *
     * @param newState
     */
    public void setTriggerAction( boolean newState ){
        triggerEnabledFlag = newState;
        // if it's enabled, run the timer
        if( newState ) triggerAction();
    }

    /**
     * Get notified if mouse is over the popup
     *
     * @param newState
     */
    public void setMouseOverFlag( boolean newState ){
        mouseOver = newState;
    }

    /**
     * Add alarm to the container
     *
     * @param alarm
     */
    public void addAlarm( Alarm alarm ){
        containerHeight += AlarmItem.HEIGHT + 10;
        resizeContainer();
        container.getChildren().add(alarm.getUiComponent().getUI());
    }

    /**
     * Remove alarm from container
     *
     * @param alarmID
     */
    public void removeAlarm(String alarmID){
        container.getChildren().remove(container.lookup("#"+alarmID));
        containerHeight -= AlarmItem.HEIGHT + 10;
        resizeContainer();
    }

    /**
     * Resize the container height after each add/remove alarm action
     */
    private void resizeContainer(){
        container.setPrefHeight(containerHeight);
        container.setMinHeight(containerHeight);
    }

}
