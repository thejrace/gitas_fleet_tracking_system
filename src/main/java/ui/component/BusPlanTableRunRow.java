package ui.component;

import ui.UIComponent;

public class BusPlanTableRunRow extends UIComponent {

    public BusPlanTableRunRow(){

    }

    public void initUI(){
        loadFXML("bus_plan_run_row");
    }

    public BusPlanTableRunRowController getController(){
        return ((BusPlanTableRunRowController)controller);
    }

}
