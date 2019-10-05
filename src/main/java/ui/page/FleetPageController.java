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
import model.Bus;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class FleetPageController extends UIPageController implements Initializable {

    @FXML
    private FlowPane uiBusContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(Map<String, Bus> data){
        for( Map.Entry<String, Bus> entry : data.entrySet() ){
            entry.getValue().getBox().initUI();
            uiBusContainer.getChildren().add(entry.getValue().getBox().getUI());
        }
    }
}
