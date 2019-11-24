/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.custom_control;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import enums.BusBoxButtonAction;
import events.bus_box.PlanPopupOpenEvent;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import lombok.Setter;
import models.Bus;
import utils.GitasEventBus;

import java.io.IOException;

public class BusBoxButton extends Button {

    @FXML
    private FontAwesomeIconView icon;

    @Setter
    private Bus bus;

    /**
     * Constructor
     *
     * @param tooltipText
     * @param iconName
     * @param iconName
     */
    public BusBoxButton(@NamedArg("tooltipText") String tooltipText, @NamedArg("iconName") String iconName, @NamedArg("action") int action ){
        // load layout
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/custom_controls/BusBoxButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();

            setText("");

            icon.setGlyphName(iconName);
            icon.setFill(Color.valueOf("#fff"));

            // click action
            setOnAction( ev -> {

                if( BusBoxButtonAction.values()[action] == BusBoxButtonAction.PLAN ){
                    GitasEventBus.post(new PlanPopupOpenEvent(bus));
                }

            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
