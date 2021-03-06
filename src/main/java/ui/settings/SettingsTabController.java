/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.settings;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SettingsTabController {

    @FXML
    protected Button uiSaveBtn;

    /**
     * Read data from SharedConfig and fill forms
     */
    public void fillForms(){

    }

    /**
     * Init common UI events
     */
    protected void initCommonEvents(){
        uiSaveBtn.setOnMouseClicked( ev -> {
            uiSaveBtn.setDisable(true);
            submitForm();
            uiSaveBtn.setDisable(false);
        });
    }

    /**
     * Submit form
     */
    protected void submitForm(){

    }
}
