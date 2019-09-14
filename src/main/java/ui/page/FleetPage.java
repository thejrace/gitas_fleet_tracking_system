/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

public class FleetPage extends UIPage{

    public FleetPage(){
        loadFXML("fleet_page");
    }

    @Override
    public FleetPageController getController(){
        return (FleetPageController)controller;
    }
}