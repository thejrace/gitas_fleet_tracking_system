package ui.component;

import enums.BusRunStatusStyleClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import models.BusRun;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BusPlanTableRunRowController implements Initializable {

    @FXML
    private HBox uiWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(BusRun busRun){

        ArrayList<String> data = busRun.getAsArrayList();

        uiWrapper.getStyleClass().add("bus-plan-tr-"+BusRunStatusStyleClass.get(busRun.getStatus()));

        for( int k = 0; k < uiWrapper.getChildren().size(); k++ ){
            Label item = (Label)uiWrapper.getChildren().get(k);
            item.setText(data.get(k));
        }
    }

}
