/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import model.Bus;

import java.net.URL;
import java.util.ResourceBundle;

public class BusBoxController implements Initializable {

    private Bus bus;

    @FXML
    private Circle uiLed;

    @FXML
    private Label uiBusCodeLabel;

    @FXML
    private Label uiPlateLabel;

    @FXML
    private Label uiRouteLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(Bus bus){
        this.bus = bus;
        updateUI();
    }

    private void updateUI(){
        uiBusCodeLabel.setText(bus.getCode());
        uiPlateLabel.setText(bus.getActivePlate());
    }

}
