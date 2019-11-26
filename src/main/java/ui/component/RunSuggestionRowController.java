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
import javafx.scene.layout.HBox;
import models.RunSuggestion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RunSuggestionRowController implements Initializable {

    @FXML
    private HBox uiContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Setter for data
     *
     * @param runSuggestion
     */
    public void setData(RunSuggestion runSuggestion){
        ArrayList<String> suggestionArray = runSuggestion.getAsArray();
        try{
            for( int k = 0; k < uiContainer.getChildren().size(); k++ ){
                Label item = (Label)uiContainer.getChildren().get(k);
                item.setText(suggestionArray.get(k));
            }
        } catch( IndexOutOfBoundsException e ){
            e.printStackTrace();
        }
    }
}
