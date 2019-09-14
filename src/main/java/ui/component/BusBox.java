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

    public BusBox(){

    }

    public void initUI(Bus bus){
        loadFXML("bus_box");
        ((BusBoxController)getController()).setData(bus);
    }

}
