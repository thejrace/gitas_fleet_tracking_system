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
import ui.custom_control.BusBoxButton;

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

    @FXML
    private BusBoxButton uiBB0, uiBB1, uiBB2, uiBB3, uiBB4, uiBB5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(Bus bus){
        this.bus = bus;

        // @todo FIX this
        uiBB0.setKey(bus.getCode());
        uiBB1.setKey(bus.getCode());
        uiBB2.setKey(bus.getCode());
        uiBB3.setKey(bus.getCode());
        uiBB4.setKey(bus.getCode());
        uiBB5.setKey(bus.getCode());

        updateUI();
    }

    private void updateUI(){
        uiBusCodeLabel.setText(bus.getCode());
        uiPlateLabel.setText(bus.getActivePlate());
    }

}
