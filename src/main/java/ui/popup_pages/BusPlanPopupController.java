/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.popup_pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import models.BusRun;
import ui.component.BusPlanTableRunRow;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BusPlanPopupController extends BusBoxPopupPageController implements Initializable {

    @FXML
    private VBox uiContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Update UI
     */
    public void updateUI(){
        initCommonEvents();

        ArrayList<BusRun> planData = bus.getRunData();

        for( BusRun busRun : planData ){
            BusPlanTableRunRow row = new BusPlanTableRunRow();
            row.initUI();
            row.getController().setData(busRun, bus.getDriverNameList());
            uiContainer.getChildren().add(row.getUI());
        }
    }

}
