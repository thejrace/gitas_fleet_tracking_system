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
import org.json.JSONArray;
import org.json.JSONObject;
import ui.component.BusBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FleetPageController extends UIPageController implements Initializable {

    @FXML
    private FlowPane uiBusContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    public void setData(JSONArray data){
        for( int k = 0; k < data.length(); k++ ){
            JSONObject bus = data.getJSONObject(k);
            BusBox box = new BusBox();
            box.initUI(new Bus(bus.getString("code"), bus.getString("official_plate"), bus.getString("active_plate")));
            uiBusContainer.getChildren().add(box.getUI());
        }
    }
}
