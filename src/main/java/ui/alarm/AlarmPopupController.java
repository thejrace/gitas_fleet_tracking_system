/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.alarm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import ui.MainScreen;

import java.net.URL;
import java.util.ResourceBundle;

public class AlarmPopupController implements Initializable {

    @FXML private FlowPane uiContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uiContainer.setOnMouseEntered(ev -> {
            MainScreen.ALARM_POPUP.setMouseOverFlag(true);
        });

        uiContainer.setOnMouseExited(ev -> {
            MainScreen.ALARM_POPUP.setMouseOverFlag(false);
        });
    }
}
