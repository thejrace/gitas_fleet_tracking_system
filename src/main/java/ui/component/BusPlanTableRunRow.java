/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import lombok.NoArgsConstructor;
import ui.UIComponent;

@NoArgsConstructor
public class BusPlanTableRunRow extends UIComponent {

    /**
     * Initializes UI
     */
    public void initUI(){
        loadFXML("bus_plan_run_row");
    }

    /**
     * Getter for controller
     *
     * @return
     */
    public BusPlanTableRunRowController getController(){
        return ((BusPlanTableRunRowController)controller);
    }

}
