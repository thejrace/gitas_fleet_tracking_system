/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import models.Bus;

import java.net.URL;
import java.util.ResourceBundle;

public class FleetPageController extends UIPageController implements Initializable {

    @FXML private FlowPane uiBusContainer;

    @FXML private HBox uiFilterContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void showFleet(){
        for( Bus bus : FleetPage.FleetController.getBuses() ){
            uiBusContainer.getChildren().add(bus.getUiComponent().getUI());
        }
    }

    public void initFilterBar(){
        uiFilterContainer.getChildren().add(FleetPage.FilterBar.getUI());
    }
}
