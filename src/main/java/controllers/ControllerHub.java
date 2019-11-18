/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

public class ControllerHub {

    /**
     * AlarmController instance
     */
    public static AlarmController AlarmController;

    /**
     * Initialize static controllers
     */
    public void initialize(){
        AlarmController = new AlarmController();
        AlarmController.initialize();
    }

}
