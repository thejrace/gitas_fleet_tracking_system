/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import lombok.NoArgsConstructor;
import models.RunSuggestion;
import ui.UIComponent;

@NoArgsConstructor
public class RunSuggestionRow extends UIComponent {

    /**
     * Initializes UI
     */
    public void initUI(){
        loadFXML("run_suggestion_row");
    }

    /**
     * Getter for controller
     *
     * @return
     */
    public RunSuggestionRowController getController(){
        return (RunSuggestionRowController)controller;
    }

    /**
     * Setter for data
     *
     * @param runSuggestion
     */
    public void setData(RunSuggestion runSuggestion){
        getController().setData(runSuggestion);
    }

}
