/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import model.Bus;
import ui.UIComponent;

public class BusBox extends UIComponent {
    private Bus bus;

    public BusBox(Bus bus){
        this.bus = bus;
    }

    public void initUI(){
        loadFXML("bus_box_default");
        ((BusBoxController)getController()).setData(bus);
    }
}
