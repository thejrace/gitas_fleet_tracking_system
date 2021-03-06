/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.block;

import controllers.ControllerHub;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;

import java.util.ResourceBundle;

public class TopSubBarController implements Initializable {

    @FXML private Button uiShowAlarmsBtn;
    @FXML private Button uiHideAlarmsBtn;
    @FXML private Button uiMarkAllAsSeenBtn;
    @FXML private Button uiMarkAllAsNotSeen;
    @FXML private Label uiUserNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb ){

        uiShowAlarmsBtn.setOnMouseClicked(ev->{
            ControllerHub.AlarmController.showAlarms();
        });

        uiHideAlarmsBtn.setOnMouseClicked(ev->{
            ControllerHub.AlarmController.hideAlarms();
        });

        uiMarkAllAsSeenBtn.setOnMouseClicked(ev->{
            ControllerHub.AlarmController.markAllAsSeen();
        });

        uiMarkAllAsNotSeen.setOnMouseClicked(ev->{
            ControllerHub.AlarmController.markAllAsNotSeen();
        });

        uiUserNameLabel.setText("Hoşgeldin, " + ControllerHub.UserController.getModel().getName());

    }

}
