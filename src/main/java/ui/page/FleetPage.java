/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import javafx.application.Platform;
import repository.BusRepository;

public class FleetPage extends UIPage{

    public FleetPage(){
        loadFXML("fleet_page");
        getController().setTitle("Filo");

        Thread thread = new Thread(() -> {
            BusRepository busRepository = new BusRepository();
            busRepository.fetchBuses();

            Platform.runLater(() -> {
                getController().setData(busRepository.getData());
            });
        });
        thread.setDaemon(true);
        thread.start();


    }

    @Override
    public FleetPageController getController(){
        return (FleetPageController)controller;
    }
}
