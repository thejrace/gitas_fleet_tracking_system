/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.custom_control;

import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import ui.page.FleetPage;

import java.io.IOException;

public class BusBoxStyleButton extends Button {

    /**
     * Constructor
     *
     * @param action
     */
    public BusBoxStyleButton(@NamedArg("action") int action ){
        // load layout
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/custom_controls/BusBoxStyleButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();

            setText(String.valueOf(action));
            setId("style_"+action);

            // select first one as active
            // @todo we will get this from user settings
            if( action == 0 ){
                getStyleClass().add("active");
            }

            // click action
            setOnAction( ev -> {
                if( FleetPage.FleetFilterController.getStyleChoice() == action ) return;

                // remove active class of the previous selection
                BusBoxStyleButton previousButton = (BusBoxStyleButton)(getParent().lookup("#style_"+FleetPage.FleetFilterController.getStyleChoice()));
                previousButton.getStyleClass().remove(3);

                FleetPage.FleetFilterController.updateStyleChoice(action);
                getStyleClass().add("active");
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
