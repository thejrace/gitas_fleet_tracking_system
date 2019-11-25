/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import com.google.common.eventbus.Subscribe;
import events.RunSuggestionsProcessFinishedEvent;
import events.RunSuggestionsStatusUpdateEvent;
import events.RunSuggestionsTriggerEvent;
import interfaces.Subscriber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.BusRun;
import models.RunSuggestion;
import ui.component.RunSuggestionItem;
import utils.GitasEventBus;
import utils.ThreadHelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class RunSuggestionsPageController extends UIPageController implements Initializable, Subscriber {

    @FXML
    private Button uiTriggerButton;

    @FXML
    private VBox uiSuggestionItemContainer;

    @FXML private Label uiNotfLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        GitasEventBus.register(this);

        uiTriggerButton.setOnMouseClicked(ev -> {
            uiTriggerButton.setDisable(true);
            uiSuggestionItemContainer.getChildren().remove(1,uiSuggestionItemContainer.getChildren().size());
            GitasEventBus.post(new RunSuggestionsTriggerEvent());
        });

    }

    @Subscribe
    private void subscribeRunSuggestionsStatusUpdateEvent(RunSuggestionsStatusUpdateEvent event){
        ThreadHelper.runOnUIThread(() -> {
            uiNotfLabel.setText(event.getActiveStatus());
        });
    }

    /**
     * Subscribe the process finish event to handle UI actions
     *
     * @param event
     */
    @Subscribe
    private void subscribeRunSuggestionsProcessFinishedEvent( RunSuggestionsProcessFinishedEvent event ){

        ThreadHelper.runOnUIThread(() -> {
            uiNotfLabel.setText("");
            uiTriggerButton.setDisable(false);
        });

        Map<BusRun, ArrayList<RunSuggestion>> data = event.getSuggestions();

        for( Map.Entry<BusRun, ArrayList<RunSuggestion>> entry : data.entrySet()){
            RunSuggestionItem runSuggestionItem = new RunSuggestionItem();
            runSuggestionItem.initUI();
            runSuggestionItem.setData(entry.getKey(), entry.getValue());

            ThreadHelper.runOnUIThread( () -> {
                uiSuggestionItemContainer.getChildren().add(runSuggestionItem.getUI());
            });
        }
    }

}
