/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

public class FleetPageController extends UIPage {

    public FleetPageController(){
        loadFXML("project_form_page");
    }

    @Override
    public FleetPageController getController(){
        return (FleetPageController)controller;
    }

}
