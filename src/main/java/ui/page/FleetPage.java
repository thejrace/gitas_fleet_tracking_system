/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import cookie_agent.CookieAgent;
import javafx.application.Platform;
import models.Bus;
import repositories.BusRepository;
import ui.popup.Popup;

import java.util.HashMap;
import java.util.Map;

public class FleetPage extends UIPage{

    private Map<String, Bus> buses = new HashMap<>();

    public FleetPage(){
        loadFXML("fleet_page");
        getController().setTitle("Filo Takip");

        initialize();
    }

    private void initialize(){

        // init cookie agent first
        CookieAgent.initialize();

        System.out.println(CookieAgent.FILO5_COOKIE);

        // get buses
        BusRepository busRepository = new BusRepository();
        busRepository.fetchBuses();

        // create bus instances
        Bus bus;
        for( int k = 0; k < busRepository.getData().length(); k++ ){
            bus = new Bus(busRepository.getData().getJSONObject(k));
            buses.put(bus.getCode(), bus);
        }

        // feed bus data to controllers
        Platform.runLater(() -> {
            getController().setData(buses);
            Popup.hide();
        });

    }

    @Override
    public FleetPageController getController(){
        return (FleetPageController)controller;
    }
}
