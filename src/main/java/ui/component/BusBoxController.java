/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import javafx.fxml.Initializable;
import model.Bus;

import java.net.URL;
import java.util.ResourceBundle;

public class BusBoxController implements Initializable {

    private Bus bus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(Bus bus){
        this.bus = bus;
        updateUI();
    }

    private void updateUI(){

    }

}
