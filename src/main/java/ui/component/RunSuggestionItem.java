package ui.component;

import models.BusRun;
import models.RunSuggestion;
import ui.UIComponent;

import java.util.ArrayList;

public class RunSuggestionItem extends UIComponent {

    public RunSuggestionItem(){

    }

    public void setData(BusRun cancelledRun, ArrayList<RunSuggestion> suggestions){
        getController().setData(cancelledRun, suggestions);
    }

    public void initUI(){
        loadFXML("run_suggestion_item");
    }

    public RunSuggestionItemController getController(){
        return (RunSuggestionItemController)controller;
    }

}
