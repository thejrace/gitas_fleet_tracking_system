/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import controllers.ControllerHub;
import controllers.FleetController;
import controllers.FleetFilterController;
import cookie_agent.CookieAgent;
import enums.AlarmType;
import models.Alarm;
import ui.block.FleetFilterBar;
import ui.popup.Popup;
import utils.ThreadHelper;

public class FleetPage extends UIPage{

    /**
     * FleetController instance
     */
    public static FleetController FleetController;

    /**
     * FleetFilterController instance
     */
    public static FleetFilterController FleetFilterController;

    /**
     * Empty constructor
     */
    public FleetPage(){
        loadFXML("fleet_page");
        getController().setTitle("Filo Takip");

        initialize();
    }

    /**
     * Initialize the page
     */
    private void initialize(){
        FleetController = new FleetController();
        FleetController.passPageController(getController());
        FleetFilterController = new FleetFilterController();

        FleetFilterBar fleetFilterBar = new FleetFilterBar();
        fleetFilterBar.initUI();
        getController().initFilterBar(fleetFilterBar.getUI());

        // init cookie agent first
        CookieAgent.initialize();

        while( !CookieAgent.checkCookie() ){
            System.out.println("Checking cookiee!");
            ThreadHelper.delay(1000);
        }

        System.out.println("Cookie is valid, continue");
        ThreadHelper.runOnUIThread( () -> {
            // show loader again for bus fetch action
            Popup.showMessage(Popup.DEFAULT, "Fetcing buses!");
        });

        FleetController.downloadBuses();

        // feed bus data to controllers
        ThreadHelper.runOnUIThread(() -> {
            getController().showFleet();
            Popup.hide();

            ControllerHub.AlarmController.start();
        });
    }

    @Override
    public FleetPageController getController(){
        return (FleetPageController)controller;
    }
}
