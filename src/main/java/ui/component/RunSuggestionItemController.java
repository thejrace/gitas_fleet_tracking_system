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
import javafx.scene.layout.VBox;
import models.BusRun;
import models.RunSuggestion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RunSuggestionItemController implements Initializable {

    @FXML
    private VBox uiContainer;

    @FXML
    private HBox uiActiveRunContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Setter for data
     *
     * @param cancelledRun
     * @param suggestions
     */
    public void setData(BusRun cancelledRun, ArrayList<RunSuggestion> suggestions) {

        ArrayList<String> cancelledRunData = cancelledRun.getAsArrayListRunSuggestions();
        for( int k = 0; k < uiActiveRunContainer.getChildren().size(); k++ ){
            Label label = (Label)uiActiveRunContainer.getChildren().get(k);
            label.setText(cancelledRunData.get(k));
        }

        for( RunSuggestion runSuggestion : suggestions ){
            RunSuggestionRow runSuggestionRow = new RunSuggestionRow();
            runSuggestionRow.initUI();
            runSuggestionRow.setData(runSuggestion);
            uiContainer.getChildren().add(runSuggestionRow.getUI());
        }

    }

}
