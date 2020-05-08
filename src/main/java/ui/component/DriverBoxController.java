/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.BusDriver;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverBoxController implements Initializable {

    @FXML
    private ImageView uiPicture;

    @FXML
    private Label uiNameLabel;

    @FXML
    private Label uiCodeLabel;

    @FXML
    private Label uiPhoneLabel;

    @FXML
    private Label uiTcLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Setter for data
     *
     * @param busDriver
     */
    public void setData(BusDriver busDriver){
        uiNameLabel.setText(busDriver.getName());
        uiCodeLabel.setText(busDriver.getCode());
        uiPhoneLabel.setText(busDriver.getPhone());
        uiTcLabel.setText(busDriver.getTcNo());
        uiPicture.setImage(new Image("https://filotakip.iett.gov.tr"+busDriver.getImgURL()));
    }

}
