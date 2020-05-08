/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import lombok.NoArgsConstructor;
import models.BusDriver;
import ui.UIComponent;

@NoArgsConstructor
public class DriverBox extends UIComponent {

    /**
     * Setter for data
     *
     * @param busDriver
     */
    public void setData(BusDriver busDriver){
        getController().setData(busDriver);
    }

    /**
     * Initializes UI
     */
    public void initUI(){
        loadFXML("driver_box");
    }

    /**
     * Getter for controller
     *
     * @return
     */
    @Override
    public DriverBoxController getController(){
        return (DriverBoxController)controller;
    }

}
