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
import interfaces.ActionCallback;
import interfaces.NoParamCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Bus;

import java.net.URL;
import java.util.ResourceBundle;

public class BusPlateFormPopupController implements Initializable {

    /**
     * Bus model
     */
    private Bus bus;

    /**
     * Bus code label in the title
     */
    @FXML private Label uiBusCodeLabel;

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

    /**
     * Close button
     */
    @FXML private FontAwesomeIconView uiCloseBtn;

    /**
     * Index of the popup page
     */
    private int pageIndex;

    /**
     * Listener to notify BusBox
     */
    private NoParamCallback listener;

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
                    listener.action();
                    unlockForm();
                }

                @Override
                public void onError(int type) {
                    unlockForm();
                }
            });
        });

        uiCloseBtn.setOnMouseClicked( ev -> {
            ControllerHub.PopupPageController.closePage(pageIndex);
        });

    }

    /**
     * Get the index of the popup page
     *
     * @param pageIndex
     */
    public void setIndex(int pageIndex){
        this.pageIndex = pageIndex;
    }

    /**
     * Set data to the layout
     *
     * @param bus
     */
    public void setData(Bus bus){
        this.bus = bus;
        uiBusCodeLabel.setText(bus.getCode());
        uiActivePlateInput.setText(bus.getActivePlate());
        uiOfficialPlateInput.setText(bus.getOfficialPlate());
    }

    /**
     * Setter for listener to notify BusBox
     *
     * @param listener
     */
    public void setListener(NoParamCallback listener ){
        this.listener = listener;
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
