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

            // click action
            setOnAction( ev -> {

                System.out.println(action);

            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
