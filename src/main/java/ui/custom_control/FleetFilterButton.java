/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.custom_control;

import enums.FleetFilterButtonAction;
import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class FleetFilterButton extends Button {

    /**
     * Constructor
     *
     * @param text
     * @param action
     */
    public FleetFilterButton(@NamedArg("text") String text, @NamedArg("className") String className, @NamedArg("action") int action ){
        // load layout
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/custom_controls/FleetFilterButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();

            setText(text);
            getStyleClass().add(className);

            // click action
            setOnAction( ev -> {

                System.out.println(FleetFilterButtonAction.values()[action]);

            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
