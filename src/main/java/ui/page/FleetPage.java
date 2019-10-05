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
import model.Bus;
import repository.BusRepository;
import ui.popup.Popup;
import utils.ThreadHelper;

import java.util.HashMap;
import java.util.Map;

public class FleetPage extends UIPage{

    private Map<String, Bus> buses = new HashMap<>();

    public FleetPage(){
        loadFXML("fleet_page");
        getController().setTitle("Filo Takip");

        Popup.showLoader();

        initialize();
    }

    private void initialize(){
        ThreadHelper.func(() -> {
            // init cookie agent first
            CookieAgent.initialize();

            // get buses
            BusRepository busRepository = new BusRepository();
            busRepository.fetchBuses();

            // create bus instances
            Bus bus;
            for( int k = 0; k < busRepository.getData().length(); k++ ){
                bus = new Bus(busRepository.getData().getJSONObject(k));
                buses.put(bus.getCode(), bus);
            }

            // feed bus data to controller
            Platform.runLater(() -> {
                getController().setData(buses);
                Popup.hide();
            });
        });
    }

    @Override
    public FleetPageController getController(){
        return (FleetPageController)controller;
    }
}
