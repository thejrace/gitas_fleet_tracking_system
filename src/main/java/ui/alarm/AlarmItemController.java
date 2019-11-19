/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.alarm;

import controllers.ControllerHub;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import enums.AlarmType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import models.Alarm;

import java.net.URL;
import java.util.ResourceBundle;

public class AlarmItemController implements Initializable {

    @FXML private HBox uiContainer;

    @FXML private FontAwesomeIconView uiIcon;

    @FXML private Label uiTitle;

    @FXML private Label uiDetails;

    /**
     * Alarm model
     */
    private Alarm alarm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uiContainer.setOnMouseClicked(ev -> {
            ControllerHub.AlarmController.markAsSeen(alarm);
        });
    }

    /**
     * Get data for view
     *
     * @param alarm
     */
    public void setData(Alarm alarm){
        this.alarm = alarm;
        uiContainer.setId(alarm.getID());

        uiTitle.setText(alarm.getTitle());
        uiDetails.setText(alarm.getDetails());

        // conditional styling
        String containerClass;
        if( alarm.getType() == AlarmType.RED ){
            containerClass = "red";
            uiIcon.setGlyphName(FontAwesomeIcon.CLOSE.name());
        } else if( alarm.getType() == AlarmType.GREEN ){
            containerClass = "green";
            uiIcon.setGlyphName(FontAwesomeIcon.CHECK.name());
        } else if( alarm.getType() == AlarmType.BLUE ){
            containerClass = "blue";
            uiIcon.setGlyphName(FontAwesomeIcon.INFO.name());
        } else if( alarm.getType() == AlarmType.WHITE ){
            containerClass = "white";
            uiIcon.setGlyphName(FontAwesomeIcon.EXCLAMATION.name());
        } else { // gray
            containerClass = "gray";
            uiIcon.setGlyphName(FontAwesomeIcon.EXCLAMATION.name());
        }
        uiContainer.getStyleClass().add(containerClass);
    }
}
