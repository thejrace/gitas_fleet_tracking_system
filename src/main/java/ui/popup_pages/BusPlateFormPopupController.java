/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.popup_pages;

import controllers.ControllerHub;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        uiActionBtn.setOnMouseClicked( ev -> {


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
        uiBusCodeLabel.setText(bus.getCode());
        uiActivePlateInput.setText(bus.getActivePlate());
        uiOfficialPlateInput.setText(bus.getOfficialPlate());
    }

}
