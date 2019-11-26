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
import ui.page.FleetPage;

import java.io.IOException;

public class FleetFilterButton extends Button {

    private boolean active = false;

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
                if( active ){
                    getStyleClass().remove(3);
                } else {
                    getStyleClass().add("active");
                }
                active = !active;
                FleetPage.FleetFilterController.updateFilterStates(FleetFilterButtonAction.values()[action], active);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
