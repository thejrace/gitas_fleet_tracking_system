/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

public class ReportsPage extends UIPage{

    public ReportsPage(){
        loadFXML("reports_page");
        getController().setTitle("Raporlar");
    }

    @Override
    public ReportsPageController getController(){
        return (ReportsPageController)controller;
    }
}
