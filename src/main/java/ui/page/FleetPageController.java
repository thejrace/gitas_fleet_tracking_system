/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import controllers.ControllerHub;
import enums.FleetFilterButtonAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Bus;
import ui.component.BusBoxController;

import java.net.URL;
import java.util.*;

public class FleetPageController extends UIPageController implements Initializable {

    @FXML private FlowPane uiBusContainer;

    @FXML private HBox uiFilterContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Apply filters to the boxes.
     */
    public void applyFilters(){
        ArrayList<Bus> fleet = ControllerHub.FleetController.getBuses();
        for( Bus bus : fleet ){
            boolean showBus = true;
            for(Map.Entry<FleetFilterButtonAction, Boolean> entry : FleetPage.FleetFilterController.getFilterStates().entrySet() ){
                if( entry.getValue() ){
                    showBus = showBus && bus.getFilterFlags().get(entry.getKey());
                }
            }
            if( !showBus ){
                VBox box = (VBox)uiBusContainer.lookup("#"+bus.getCode());
                uiBusContainer.getChildren().remove(box);
            } else {
                try {
                    uiBusContainer.getChildren().add(bus.getUiComponent().getUI());
                } catch( IllegalArgumentException e ){
                    //e.printStackTrace();
                }
            }
        }
        sortBusBoxes();
    }

    /**
     * Apply bus box style changes to the boxes.
     */
    public void applyStyles(){
        ArrayList<Bus> fleet = ControllerHub.FleetController.getBuses();
        for( Bus bus : fleet ){
            ((BusBoxController)(bus.getUiComponent().getController())).applyStyle(FleetPage.FleetFilterController.getStyleChoice());
        }
    }

    /**
     * Initial fleet creation
     */
    public void showFleet(){
        for( Bus bus : ControllerHub.FleetController.getBuses() ){
            uiBusContainer.getChildren().add(bus.getUiComponent().getUI());
        }
        sortBusBoxes();
    }

    /**
     * Get filterContainer ( called from FleetPage )
     *
     * @param filterBarUI
     */
    public void initFilterBar(Node filterBarUI){
        uiFilterContainer.getChildren().add(filterBarUI);
    }

    /**
     * Sort bus boxes according to their codes
     */
    private void sortBusBoxes(){
        try {
            ObservableList<Node> obs_array = FXCollections.observableArrayList( uiBusContainer.getChildren() );
            Collections.sort(obs_array, Comparator.comparing(vb -> vb.getId().replace("-", "")));
            uiBusContainer.getChildren().setAll(obs_array);
        } catch( IndexOutOfBoundsException e ){
            e.printStackTrace();
        }
    }
}
