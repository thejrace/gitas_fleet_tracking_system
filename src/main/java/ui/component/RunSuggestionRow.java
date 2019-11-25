package ui.component;

import lombok.NoArgsConstructor;
import models.RunSuggestion;
import ui.UIComponent;

@NoArgsConstructor
public class RunSuggestionRow extends UIComponent {

    public void initUI(){
        loadFXML("run_suggestion_row");
    }

    public RunSuggestionRowController getController(){
        return (RunSuggestionRowController)controller;
    }

    public void setData(RunSuggestion runSuggestion){
        getController().setData(runSuggestion);
    }

}
