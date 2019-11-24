/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import events.RunSuggestionsTriggerEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import utils.GitasEventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class RunSuggestionsPageController extends UIPageController implements Initializable {

    @FXML
    private Button uiTriggerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        uiTriggerButton.setOnMouseClicked(ev -> {
            GitasEventBus.post(new RunSuggestionsTriggerEvent());
        });

    }
}
