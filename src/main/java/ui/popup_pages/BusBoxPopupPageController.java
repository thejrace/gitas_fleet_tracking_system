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
import javafx.scene.control.Label;
import lombok.Setter;
import models.Bus;

/**
 * Base class for BusBox popup pages
 */
public class BusBoxPopupPageController {

    /**
     * Index of the popup page
     */
    @Setter
    protected int pageIndex;

    /**
     * Bus model
     */
    @Setter
    protected Bus bus;

    /**
     * Close button
     */
    @FXML
    protected FontAwesomeIconView uiCloseBtn;

    /**
     * Bus code label in the title
     */
    @FXML
    protected Label uiBusCodeLabel;

    /**
     * Init common events in all pages
     */
    protected void initCommonEvents(){
        uiBusCodeLabel.setText(bus.getCode());

        uiCloseBtn.setOnMouseClicked( ev -> {
            ControllerHub.PopupPageController.closePage(pageIndex);
        });
    }

}
