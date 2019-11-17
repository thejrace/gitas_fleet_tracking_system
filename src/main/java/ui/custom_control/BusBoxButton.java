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
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.io.IOException;

public class BusBoxButton extends Button {

    @FXML
    private FontAwesomeIconView icon;

    private String busCode;

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

                // @todo, static bus list e ulaşıp, tetik vericez

                System.out.println(BusBoxButtonAction.values()[action] + " ---  " + busCode );

            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setKey( String key ){
        busCode = key;
    }

}
