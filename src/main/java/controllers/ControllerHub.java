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
     * UserController instance
     */
    public static UserController UserController;

    /**
     * FleetController instance
     */
    public static FleetController FleetController;

    /**
     * PopupPageController instance
     */
    public static PopupPageController PopupPageController;


    /**
     * Initialize static controllers
     */
    public void initialize(){
        AlarmController = new AlarmController();
        AlarmController.initialize();

        PopupPageController = new PopupPageController();
        PopupPageController.initialize();

        UserController = new UserController();

        FleetController = new FleetController();
    }

}
