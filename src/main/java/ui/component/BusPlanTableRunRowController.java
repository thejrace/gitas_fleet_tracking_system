/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import enums.BusRunStatusStyleClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import models.BusRun;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class BusPlanTableRunRowController implements Initializable {

    @FXML
    private HBox uiWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Setter for data
     *
     * @param busRun
     */
    public void setData(BusRun busRun, Map<String, String> driverNameList){

        ArrayList<String> data = busRun.getAsArrayList();

        uiWrapper.getStyleClass().add("bus-plan-tr-"+BusRunStatusStyleClass.get(busRun.getStatus()));

        for( int k = 0; k < uiWrapper.getChildren().size(); k++ ){
            Label item = (Label)uiWrapper.getChildren().get(k);
            // NOTE: We make driver-drivercode replacement here.
            // We could replace codes with names in bus' run data,
            // BUT at every fleet data download action, we had to make replace action again and again.
            // That's why we do it here, when it's needed.
            if( driverNameList.containsKey(data.get(k))){
                item.setText(driverNameList.get(data.get(k)));
            } else {
                item.setText(data.get(k));
            }
        }
    }

}
