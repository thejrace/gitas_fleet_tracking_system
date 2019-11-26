/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import controllers.ControllerHub;
import controllers.FleetFilterController;
import cookie_agent.CookieAgent;
import ui.block.FleetFilterBar;
import ui.popup.Popup;
import utils.ThreadHelper;

public class FleetPage extends UIPage{

    /**
     * FleetFilterController instance
     */
    public static FleetFilterController FleetFilterController;

    /**
     * Empty constructor
     */
    public FleetPage(){
        loadFXML("fleet_page");

        ControllerHub.FleetController.setFleetPage(this);

        getController().setTitle("Filo Takip");

        initialize();

    }

    /**
     * Initialize the page
     */
    private void initialize(){
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

        ControllerHub.FleetController.downloadBuses();

        // feed bus data to controllers
        ThreadHelper.runOnUIThread(() -> {
            getController().showFleet();
            Popup.hide();

            ControllerHub.AlarmController.start();
        });
    }

    /**
     * Notify UI controller to apply filters
     */
    public void applyFilters(){
        getController().applyFilters();
    }

    /**
     * Notify UI controller to apply styles
     */
    public void applyStyles(){
        getController().applyStyles();
    }

    @Override
    public FleetPageController getController(){
        return (FleetPageController)controller;
    }
}
