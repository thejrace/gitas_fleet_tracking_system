/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.alarm;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import enums.AlarmType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Alarm;
import ui.MainScreen;

import java.net.URL;
import java.util.ResourceBundle;

public class AlarmItemController implements Initializable {

    @FXML private HBox uiContainer;

    @FXML private FontAwesomeIconView uiIcon;

    @FXML private Label uiTitle;

    @FXML private Label uiDetails;

    private String ID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uiContainer.setOnMouseClicked(ev -> {
            MainScreen.ALARM_POPUP.removeAlarm(ID);
        });
    }

    /**
     * Get data for view
     *
     * @param alarmData
     */
    public void setData(Alarm alarmData){
        // assign a unique ID to container for seen action
        ID = alarmData.getType().name()+"-"+alarmData.getTitle();
        uiContainer.setId(ID);

        uiTitle.setText(alarmData.getTitle());
        uiDetails.setText(alarmData.getDetails());

        // conditional styling
        String containerClass;
        if( alarmData.getType() == AlarmType.RED ){
            containerClass = "red";
            uiIcon.setGlyphName(FontAwesomeIcon.CLOSE.name());
        } else if( alarmData.getType() == AlarmType.GREEN ){
            containerClass = "green";
            uiIcon.setGlyphName(FontAwesomeIcon.CHECK.name());
        } else if( alarmData.getType() == AlarmType.BLUE ){
            containerClass = "blue";
            uiIcon.setGlyphName(FontAwesomeIcon.INFO.name());
        } else if( alarmData.getType() == AlarmType.WHITE ){
            containerClass = "white";
            uiIcon.setGlyphName(FontAwesomeIcon.EXCLAMATION.name());
        } else { // gray
            containerClass = "gray";
            uiIcon.setGlyphName(FontAwesomeIcon.EXCLAMATION.name());
        }
        uiContainer.getStyleClass().add(containerClass);
    }
}
