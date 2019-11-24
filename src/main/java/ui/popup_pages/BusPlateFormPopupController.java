/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.popup_pages;

import controllers.BusController;
import controllers.ControllerHub;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import events.bus_box.PlateUpdateEvent;
import interfaces.ActionCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.GitasEventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class BusPlateFormPopupController extends BusBoxPopupPageController implements Initializable {

    /**
     * Active plate input field
     */
    @FXML private TextField uiActivePlateInput;

    /**
     * Official plate input field
     */
    @FXML private TextField uiOfficialPlateInput;

    /**
     * Submit button
     */
    @FXML private Button uiActionBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        uiActionBtn.setOnMouseClicked( ev -> {
            lockForm();
            //todo check form
            bus.setActivePlate(uiActivePlateInput.getText());
            bus.setOfficialPlate(uiOfficialPlateInput.getText());
            BusController.updatePlateData(bus, new ActionCallback() {
                @Override
                public void onSuccess(String... params) {
                    // trigger plate fetch
                    //listener.action();
                    GitasEventBus.post(new PlateUpdateEvent(bus));
                    System.out.println("post");
                    unlockForm();
                }

                @Override
                public void onError(int type) {
                    unlockForm();
                }
            });
        });

    }

    /**
     * Set data to the layout
     */
    public void updateUI(){
        initCommonEvents();

        uiActivePlateInput.setText(bus.getActivePlate());
        uiOfficialPlateInput.setText(bus.getOfficialPlate());
    }

    /**
     * Lock form elements
     */
    private void lockForm(){
        uiActivePlateInput.setDisable(true);
        uiOfficialPlateInput.setDisable(true);
        uiActionBtn.setDisable(true);
    }

    /**
     * Unlock form elements
     */
    private void unlockForm(){
        uiActivePlateInput.setDisable(false);
        uiOfficialPlateInput.setDisable(false);
        uiActionBtn.setDisable(false);
    }

}
