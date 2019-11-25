/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import lombok.NoArgsConstructor;
import models.BusRun;
import models.RunSuggestion;
import ui.UIComponent;

import java.util.ArrayList;

@NoArgsConstructor
public class RunSuggestionItem extends UIComponent {

    /**
     * Setter for data
     *
     * @param cancelledRun
     * @param suggestions
     */
    public void setData(BusRun cancelledRun, ArrayList<RunSuggestion> suggestions){
        getController().setData(cancelledRun, suggestions);
    }

    /**
     * Initializes UI
     */
    public void initUI(){
        loadFXML("run_suggestion_item");
    }

    /**
     * Getter for controller
     *
     * @return
     */
    public RunSuggestionItemController getController(){
        return (RunSuggestionItemController)controller;
    }

}
