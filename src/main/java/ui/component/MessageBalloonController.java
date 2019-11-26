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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.BusMessage;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageBalloonController implements Initializable {

    @FXML
    private HBox uiWrapper;

    @FXML
    private HBox uiContent;

    @FXML
    private VBox uiLeftTick;

    @FXML
    private VBox uiRightTick;

    @FXML
    private Label uiSource;

    @FXML
    private Label uiTimestamp;

    @FXML
    private Label uiMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Setter for data
     *
     * @param busMessage
     */
    public void setData(BusMessage busMessage){
        // set alignment according to the source
        if( busMessage.getSource().equals("GUI") ){
            uiContent.getStyleClass().add("right");
            uiWrapper.getChildren().remove(uiLeftTick);
            uiWrapper.setAlignment(Pos.TOP_RIGHT);
        } else {
            uiContent.getStyleClass().add("left");
            uiWrapper.getChildren().remove(uiRightTick);
            uiWrapper.setAlignment(Pos.TOP_LEFT);
        }

        uiSource.setText(busMessage.getSource());
        uiMessage.setText(busMessage.getMessage());
        uiTimestamp.setText(busMessage.getTimestamp());
    }
}
